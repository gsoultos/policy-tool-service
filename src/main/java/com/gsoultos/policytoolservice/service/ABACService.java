package com.gsoultos.policytoolservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsoultos.policytoolservice.common.Constants;
import com.gsoultos.policytoolservice.exception.AbacGenerationException;
import lombok.NonNull;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service(Constants.ServiceName.ABAC)
public class ABACService {
  private final Map<String, Policy> policies;

  public ABACService() {
    this.policies = new HashMap<>();
  }

  public final void addPolicy(@NonNull Policy policy) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();

      List<Object> rules = new ArrayList<>();
      for (Object o :
          policy.getCombinerParametersAndRuleCombinerParametersAndVariableDefinitions()) {
        rules.add(objectMapper.readValue(objectMapper.writeValueAsBytes(o), Rule.class));
      }

      this.policies.put(
          policy.getPolicyId(),
          new Policy(
              policy.getDescription(),
              null,
              null,
              policy.getTarget(),
              rules,
              null,
              null,
              policy.getPolicyId(),
              policy.getVersion(),
              policy.getRuleCombiningAlgId(),
              policy.getMaxDelegationDepth()));
    } catch (IOException e) {
      throw new AbacGenerationException(e.getMessage());
    }
  }

  public final Policy getPolicy(@NonNull String policyId) {
    return this.policies.get(policyId);
  }

  public final Map<String, Policy> getPolicies() {
    return this.policies;
  }

  public final void deletePolicy(@NonNull String policyId) {
    this.policies.remove(policyId);
  }

  public final void deleteAllPolicies() {
    this.policies.clear();
  }
}
