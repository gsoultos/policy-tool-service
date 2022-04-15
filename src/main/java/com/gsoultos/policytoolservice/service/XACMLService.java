package com.gsoultos.policytoolservice.service;

import com.gsoultos.policytoolservice.common.Constants;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.Policy;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.Request;
import org.ow2.authzforce.sdk.core.Utils;
import org.ow2.authzforce.sdk.core.schema.category.ActionCategory;
import org.ow2.authzforce.sdk.core.schema.category.EnvironmentCategory;
import org.ow2.authzforce.sdk.core.schema.category.ResourceCategory;
import org.ow2.authzforce.sdk.core.schema.category.SubjectCategory;
import org.ow2.authzforce.sdk.exceptions.XacmlSdkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@Service(Constants.ServiceName.REQUEST)
public class XACMLService {
  private final Map<String, CategoryTemplate> categoryServices;
  private final ABACService abacService;

  @Autowired
  public XACMLService(Map<String, CategoryTemplate> categoryServices, ABACService abacService) {
    this.categoryServices = categoryServices;
    this.abacService = abacService;
  }

  public String generateABAC(String policyId) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(Policy.class);
    Marshaller marshaller = jaxbContext.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    StringWriter out = new StringWriter();
    marshaller.marshal(this.abacService.getPolicy(policyId), out);
    return out.toString();
  }

  public Request generateRequest() throws XacmlSdkException {
    return Utils.createXacmlRequest(
        List.of(
            (SubjectCategory) categoryServices.get(Constants.ServiceName.SUBJECT).getCategory()),
        List.of(
            (ResourceCategory) categoryServices.get(Constants.ServiceName.RESOURCE).getCategory()),
        List.of((ActionCategory) categoryServices.get(Constants.ServiceName.ACTION).getCategory()),
        List.of(
            (EnvironmentCategory)
                categoryServices.get(Constants.ServiceName.ENVIRONMENT).getCategory()));
  }
}
