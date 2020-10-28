# KieServerExtension
This project is a Kie server extension for extracting the deployed rules content in the KieServer

# generate JAR file
mvn clean package

#Copy to KieServer Directory 

cp -rp KieServerReader-0.0.1-SNAPSHOT.jar $EAP_HOME/standalone/deployments/kie-server.war/WEB-INF/lib

# Test via Post man

Http Method : Post
URL : http://localhost:8080/kie-server/services/rest/server/kieserver/read/defintions
Header : Content-Type : application/json

# Sample Response :
{
    "containers": [
        {
            "containerName": "test-rules_1.0.0-SNAPSHOT",
            "rules": [
                {
                    "ruleName": "Test1",
                    "packageName": "com.test",
                    "content": "package com.test \n\nimport java.util.List;\nimport java.util.ArrayList;\n\n//from row number: 1\n//rule 1\nrule \"Test1\"\n\tdialect \"mvel\"\n\twhen\n\t\t$c : Country( name == \"Argentina\" )\n\t\t$e : Entity( type == \"Organization\" )\n\tthen\n\t\t$e.addSectorWithSubsectors(\"Public\", \"School, Medical, Police\");\n\t\t$e.setRawUniqueId(\"Tax Identifier\", \"[0-9]{3}-[0-9]{2}-[0-9]{4}\", \"XXX-XX-XXXX\", \"Unique Id does not follow the pattern (eg. 999-99-9999)\");\nend\n\n//from row number: 2\n//rule 2\nrule \"Test2\"\n\tdialect \"mvel\"\n\twhen\n\t\t$c : Country( name == \"Argentina\" )\n\t\t$e : Entity( type == \"Organization\" )\n\tthen\n\t\t$e.addSectorWithSubsectors(\"Private\", \"University, Construction, Automobile\");\n\t\t$e.setRawUniqueId(\"Tax Identifier\", \"[0-9]{3}-[0-9]{2}-[0-9]{4}\", \"XXX-XX-XXXX\", \"Unique Id does not follow the pattern (eg. 999-99-9999)\");\nend\n\n",
                    "ruleId": "Test1"
                }]
         }
}         
               
