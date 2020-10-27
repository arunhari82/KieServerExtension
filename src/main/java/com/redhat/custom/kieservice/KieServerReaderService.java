package com.redhat.custom.kieservice;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import org.kie.server.services.api.KieServerApplicationComponentsService;
import org.kie.server.services.api.KieServerRegistry;
import org.kie.server.services.api.SupportedTransports;
import org.kie.server.services.drools.RulesExecutionService;

public class KieServerReaderService implements KieServerApplicationComponentsService {

	private static final String OWNER_EXTENSION = "Drools";

	@Override
	public Collection<Object> getAppComponents(String extension, SupportedTransports type, Object... services) {
		// TODO Auto-generated method stub
		if (!OWNER_EXTENSION.equals(extension) || !SupportedTransports.REST.equals(type)) {
			return Collections.emptyList();
		}
		
		RulesExecutionService rulesExecutionService = findByType(services, RulesExecutionService.class);
		KieServerRegistry context = findByType(services, KieServerRegistry.class);
		Object resource = new KieServerReaderRestService(context);
		return Arrays.asList(resource);	
	
	}

	@SuppressWarnings("unchecked")
	private <T> T findByType(Object[] services, Class<T> clazz) {
		return Stream.of(services).filter(svc -> clazz.isAssignableFrom(svc.getClass())).map(svc -> (T) svc).findFirst()
				.get();
	}

}
