package com.redhat.custom.kieservice;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.drools.core.base.mvel.MVELConsequence;
import org.drools.core.definitions.rule.impl.RuleImpl;
import org.drools.core.rule.ConsequenceMetaData;
import org.drools.core.rule.ConsequenceMetaData.Statement;
import org.drools.core.rule.Declaration;
import org.drools.core.rule.GroupElement;
import org.drools.core.rule.RuleConditionElement;
import org.drools.core.rule.constraint.MvelConstraint;
import org.drools.core.spi.Consequence;
import org.drools.core.spi.Constraint;
import org.drools.core.rule.Pattern;
import org.kie.api.KieBase;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.utils.KieHelper;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.services.api.KieServerRegistry;
import org.kie.server.services.impl.KieContainerInstanceImpl;

import com.redhat.custom.kieservice.models.ContainerInfo;
import com.redhat.custom.kieservice.models.KieServerDefnResponse;
import com.redhat.custom.kieservice.models.RuleDefn;

@Path("/server/kieserver/read")
public class KieServerReaderRestService {

	private KieServerRegistry registry;
	private KieBase kbase;

	public KieServerReaderRestService(KieServerRegistry registry) {

		this.registry = registry;

	}

	@POST
	@Path("/defintions")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertFireReturn(@Context HttpHeaders headers, @PathParam("containerId") String id,
			@PathParam("ksessionId") String ksessionId, String cmdPayload) {
		
		KieServerDefnResponse resp = new KieServerDefnResponse();
		resp.setContainers(new ArrayList<ContainerInfo>());

		for (KieContainerInstanceImpl container : registry.getContainers()) {
			ContainerInfo info = new ContainerInfo();
			info.setContainerName(container.getKieContainer().getContainerId());
			this.kbase = container.getKieContainer().getKieBase();
			KieContainerResource resource = container.getResource();

			ArrayList<RuleDefn> rulelist = new ArrayList<RuleDefn>();
			for (KiePackage kPackage : kbase.getKiePackages()) {

				for (Rule rule : kPackage.getRules()) {
					RuleDefn defn = new RuleDefn();
					defn.setRuleId(rule.getId());
					defn.setRuleName(rule.getName());
					defn.setPackageName(rule.getPackageName());
					
					System.out.println("rule.getName() : " + rule.getName());
					System.out.println("rule.getId() : " + rule.getId());
					System.out.println("rule.getPackageName() : " + rule.getPackageName());
					System.out.println("rule.getKnowledgeType() : " + rule.getKnowledgeType());
					System.out.println("rule.getClass : " + rule.getClass());

					RuleImpl impl = (RuleImpl) rule;

					System.out.println("Source Path : " + impl.getResource().getSourcePath());
					
					
					  String targetString = ""; try { Reader reader =
					  impl.getResource().getReader(); int intValueOfChar;
					  
					  while ((intValueOfChar = reader.read()) != -1) { targetString += (char)
					  intValueOfChar; } reader.close(); } catch (Exception e) {
					  e.printStackTrace();
					  
					  }
					  
					  System.out.println("Content :  " + targetString);
					  
					  defn.setContent(targetString);
					  rulelist.add(defn);

			/*		GroupElement conditions = impl.getLhs();
					for (RuleConditionElement element : conditions.getChildren()) {
						Map<String, Declaration> outerdecmap = element.getOuterDeclarations();
						for (String key : outerdecmap.keySet()) {
							System.out.println("Outer Condition : " + outerdecmap.get(key).getPattern().toString());
							
							try {
								Pattern pattern = outerdecmap.get(key).getPattern();
								for (MvelConstraint constraint : pattern.getCombinableConstraints()) {
									System.out.println("Constraint " + constraint.getExpression());
								}
								for (Constraint cons : pattern.getConstraints()) {
									System.out.println("Constraint " + cons.getClass() + " , " + cons);
								}

								// outerdecmap.get(key).getPattern().getDeclaration().getExtractor().

								System.out.println("Pattern Source " + pattern.getSource().toString());
							} catch (Exception e) {

							}
						}

						
					}

					Consequence action = impl.getConsequence();
					System.out.println(
							"Action : " + action.toString() + " , " + action.getName() + " , " + action.getClass());

					if (action instanceof MVELConsequence) {
						try {
							MVELConsequence realAction = (MVELConsequence) action;
							System.out.println("MVEL Action Name: " + realAction.getName());
							System.out.println("MVEL Action : " + realAction.getCompExpr());

						} catch (Exception e) {

						}

					}

					ConsequenceMetaData mdata = impl.getConsequenceMetaData();
					if (mdata != null && mdata.getStatements() != null) {
						for (Statement statement : mdata.getStatements()) {
							System.out.println("Statement : " + statement);
						}
					}

					Rule currentRule = kbase.getRule(rule.getPackageName(), rule.getName());
					KieHelper helper = new KieHelper();

					Map<String, Object> metadataMap = rule.getMetaData();
					for (String key : metadataMap.keySet()) {
						System.out.println("Key : " + key + "Object : " + metadataMap.get(key));
					} */

				} 
			}
			info.setRules(rulelist);
			//info.setSourcePath(sourcePath);
			resp.getContainers().add(info);
		}

		return Response
			      .status(Response.Status.OK)
			      .entity(resp)
			      .build();
	}

}
