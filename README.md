# policy-tool-service

> Policy Tool backend service

# Installation instruction

1. `docker-compose up -d`
2. `docker container exec -it policy-tool-service /bin/bash`
3. `mvn install`
4. `mvn spring-boot:run`

# Generate OpenAPI specification

## API

`java -jar openapi-generator-cli.jar generate -g typescript-axios --additional-properties supportsES6=true -i ./policy-tool-service/swagger.yaml -o typescript-client`

## Models

`java -jar openapi-generator-cli.jar generate -g typescript-node --additional-properties supportsES6=true --global-property models="Attribute:AttributeValueType:Policy:AdviceExpressions:AdviceExpression:ObligationExpressions:ObligationExpression:AttributeAssignmentExpression:Target:AnyOf:AllOf:Match:AttributeDesignatorType:AttributeSelectorType:DefaultsType:PolicyIssuer:Content:Rule:Error",supportingFiles=models.ts -i ./policy-tool-service/swagger.yaml -o typescript-models`