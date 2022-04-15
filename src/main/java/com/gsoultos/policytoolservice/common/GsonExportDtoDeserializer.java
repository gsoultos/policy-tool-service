package com.gsoultos.policytoolservice.common;

import com.google.gson.*;
import com.gsoultos.policytoolservice.dto.ExportDto;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.*;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GsonExportDtoDeserializer implements JsonDeserializer<ExportDto> {
  @Override
  public ExportDto deserialize(
      JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
      throws JsonParseException {
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    List<Attribute> subject = attributeDeserializer(jsonObject.getAsJsonArray("subject"));
    List<Attribute> resource = attributeDeserializer(jsonObject.getAsJsonArray("resource"));
    List<Attribute> action = attributeDeserializer(jsonObject.getAsJsonArray("action"));
    List<Policy> policies = policyDeserializer(jsonObject.getAsJsonArray("policies"));

    return new ExportDto(subject, resource, action, policies);
  }

  private List<Attribute> attributeDeserializer(JsonArray jsonAttributes) {
    List<Attribute> attributes = new ArrayList<>();

    jsonAttributes.forEach(
        attribute -> {
          JsonObject jsonAttribute = attribute.getAsJsonObject();

          List<AttributeValueType> attributeValues = new ArrayList<>();
          jsonAttribute
              .getAsJsonArray("attributeValues")
              .forEach(
                  attributeValue ->
                      attributeValues.add(
                          attributeValueTypeDeserializer(attributeValue.getAsJsonObject())));

          String attributeId = jsonAttribute.get("attributeId").getAsString();
          String issuer = jsonAttribute.get("issuer").getAsString();
          boolean includeInResult = jsonAttribute.get("includeInResult").getAsBoolean();

          attributes.add(new Attribute(attributeValues, attributeId, issuer, includeInResult));
        });

    return attributes;
  }

  private AttributeValueType attributeValueTypeDeserializer(JsonObject attributeValue) {
    List<Serializable> content = new ArrayList<>();
    attributeValue
        .getAsJsonArray("content")
        .forEach(jsonContent -> content.add(jsonContent.getAsString()));
    String dataType = attributeValue.get("dataType").getAsString();
    return new AttributeValueType(content, dataType, null);
  }

  private List<Policy> policyDeserializer(JsonArray jsonPolicies) {
    List<Policy> policies = new ArrayList<>();

    jsonPolicies.forEach(
        policy -> {
          JsonObject jsonPolicy = policy.getAsJsonObject();
          String description = jsonPolicy.get("description").getAsString();
          Target target = targetDeserializer(jsonPolicy.get("target").getAsJsonObject());
          List<Object> combinerParametersAndRuleCombinerParametersAndVariableDefinitions =
              combinerParametersAndRuleCombinerParametersAndVariableDefinitionsDeserializer(
                  jsonPolicy
                      .get("combinerParametersAndRuleCombinerParametersAndVariableDefinitions")
                      .getAsJsonArray());
          String policyId = jsonPolicy.get("policyId").getAsString();
          String version = jsonPolicy.get("version").getAsString();
          String ruleCombiningAlgId = jsonPolicy.get("ruleCombiningAlgId").getAsString();
          BigInteger maxDelegationDepth = jsonPolicy.get("maxDelegationDepth").getAsBigInteger();

          policies.add(
              new Policy(
                  description,
                  null,
                  null,
                  target,
                  combinerParametersAndRuleCombinerParametersAndVariableDefinitions,
                  null,
                  null,
                  policyId,
                  version,
                  ruleCombiningAlgId,
                  maxDelegationDepth));
        });

    return policies;
  }

  private Target targetDeserializer(JsonObject target) {
    List<AnyOf> anyOves = new ArrayList<>();

    target
        .get("anyOves")
        .getAsJsonArray()
        .forEach(
            anyOve -> {
              List<AllOf> allOves = new ArrayList<>();
              anyOve
                  .getAsJsonObject()
                  .get("allOves")
                  .getAsJsonArray()
                  .forEach(
                      allOve -> {
                        List<Match> matches = new ArrayList<>();
                        allOve
                            .getAsJsonObject()
                            .get("matches")
                            .getAsJsonArray()
                            .forEach(
                                match -> {
                                  JsonObject jsonMach = match.getAsJsonObject();
                                  AttributeValueType attributeValue =
                                      attributeValueTypeDeserializer(
                                          jsonMach.getAsJsonObject("attributeValue"));
                                  AttributeDesignatorType attributeDesignator =
                                      attributeDesignatorDeserializer(
                                          jsonMach.getAsJsonObject("attributeDesignator"));
                                  String matchId = jsonMach.get("matchId").getAsString();
                                  matches.add(
                                      new Match(
                                          attributeValue, null, attributeDesignator, matchId));
                                });
                        allOves.add(new AllOf(matches));
                      });
              anyOves.add(new AnyOf(allOves));
            });

    return new Target(anyOves);
  }

  private AttributeDesignatorType attributeDesignatorDeserializer(JsonObject attributeDesignator) {
    String category = attributeDesignator.get("category").getAsString();
    String attributeId = attributeDesignator.get("attributeId").getAsString();
    String dataType = attributeDesignator.get("dataType").getAsString();
    String issuer = attributeDesignator.get("dataType").getAsString();
    boolean mustBePresent = attributeDesignator.get("mustBePresent").getAsBoolean();

    return new AttributeDesignatorType(category, attributeId, dataType, issuer, mustBePresent);
  }

  private List<Object>
      combinerParametersAndRuleCombinerParametersAndVariableDefinitionsDeserializer(
          JsonArray jsonCombinerParametersAndRuleCombinerParametersAndVariableDefinitions) {
    List<Object> combinerParametersAndRuleCombinerParametersAndVariableDefinitions =
        new ArrayList<>();

    jsonCombinerParametersAndRuleCombinerParametersAndVariableDefinitions.forEach(
        combinerParametersAndRuleCombinerParametersAndVariableDefinition -> {
          JsonObject jsonCombinerParametersAndRuleCombinerParametersAndVariableDefinition =
              combinerParametersAndRuleCombinerParametersAndVariableDefinition.getAsJsonObject();
          String description =
              jsonCombinerParametersAndRuleCombinerParametersAndVariableDefinition
                  .get("description")
                  .getAsString();
          Target target =
              targetDeserializer(
                  jsonCombinerParametersAndRuleCombinerParametersAndVariableDefinition
                      .get("target")
                      .getAsJsonObject());
          String ruleId =
              jsonCombinerParametersAndRuleCombinerParametersAndVariableDefinition
                  .get("ruleId")
                  .getAsString();
          String effect =
              jsonCombinerParametersAndRuleCombinerParametersAndVariableDefinition
                  .get("effect").getAsString();

          combinerParametersAndRuleCombinerParametersAndVariableDefinitions.add(
              new Rule(
                  description, target, null, null, null, ruleId, EffectType.valueOf(effect)));
        });

    return combinerParametersAndRuleCombinerParametersAndVariableDefinitions;
  }
}
