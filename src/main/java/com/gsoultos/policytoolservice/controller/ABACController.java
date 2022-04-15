package com.gsoultos.policytoolservice.controller;

import com.gsoultos.policytoolservice.service.ABACService;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/abac")
public class ABACController {
  private final ABACService abacService;

  @Autowired
  public ABACController(ABACService abacService) {
    this.abacService = abacService;
  }

  @PostMapping
  void addPolicy(@RequestBody Policy policy) {
    this.abacService.addPolicy(policy);
  }

  @GetMapping
  Map<String, Policy> getPolicies() {
    return this.abacService.getPolicies();
  }

  @GetMapping("/{policyId}")
  Policy getPolicy(@PathVariable String policyId) {
    return this.abacService.getPolicy(policyId);
  }

  @DeleteMapping("{policyId}")
  void deletePolicy(@PathVariable String policyId) {
    this.abacService.deletePolicy(policyId);
  }
}
