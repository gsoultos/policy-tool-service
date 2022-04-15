package com.gsoultos.policytoolservice.controller;

import com.gsoultos.policytoolservice.exception.AbacGenerationException;
import com.gsoultos.policytoolservice.exception.XacmlRequestGenerationException;
import com.gsoultos.policytoolservice.service.XACMLService;
import org.ow2.authzforce.sdk.exceptions.XacmlSdkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@CrossOrigin
@RestController
@RequestMapping("/xacml")
public class XACMLController {
  @Autowired private XACMLService XACMLService;

  @GetMapping("/abac/{policyId}")
  String generateXACMLABACPolicy(@PathVariable String policyId) {
    try {
      return XACMLService.generateABAC(policyId);
    } catch (JAXBException e) {
      throw new AbacGenerationException(e.getMessage());
    }
  }

  @GetMapping("/request")
  String generateXACMLRequest() {
    try {
      return XACMLService.generateRequest().toString();
    } catch (XacmlSdkException e) {
      throw new XacmlRequestGenerationException(e.getMessage());
    }
  }
}
