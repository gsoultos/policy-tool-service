package com.gsoultos.policytoolservice.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gsoultos.policytoolservice.common.Constants;
import com.gsoultos.policytoolservice.common.GsonExportDtoDeserializer;
import com.gsoultos.policytoolservice.dto.ExportDto;
import lombok.NonNull;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.Policy;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(Constants.ServiceName.PROJECT)
public class ProjectService {
  private final Map<String, CategoryTemplate> categoryServices;
  private final ABACService abacService;

  @Autowired
  public ProjectService(Map<String, CategoryTemplate> categoryServices, ABACService abacService) {
    this.categoryServices = categoryServices;
    this.abacService = abacService;
  }

  public String exportData() {
    List<Attribute> subjectAttributes =
        new ArrayList<>(
            categoryServices.get(Constants.ServiceName.SUBJECT).getAttributes().values());
    List<Attribute> resourceAttributes =
        new ArrayList<>(
            categoryServices.get(Constants.ServiceName.RESOURCE).getAttributes().values());
    List<Attribute> actionAttributes =
        new ArrayList<>(
            categoryServices.get(Constants.ServiceName.ACTION).getAttributes().values());
    List<Policy> policies = new ArrayList<>(abacService.getPolicies().values());

    return new GsonBuilder()
        .registerTypeAdapter(ExportDto.class, new GsonExportDtoDeserializer())
        .create()
        .toJson(new ExportDto(subjectAttributes, resourceAttributes, actionAttributes, policies));
  }

  public void loadData(@NonNull String exportedData) {
    ExportDto exportDtos =
        new GsonBuilder()
            .registerTypeAdapter(ExportDto.class, new GsonExportDtoDeserializer())
            .create()
            .fromJson(exportedData, ExportDto.class);

    categoryServices.get(Constants.ServiceName.SUBJECT).deleteAllAttributes();
    for (Attribute attribute : exportDtos.getSubject()) {
      categoryServices.get(Constants.ServiceName.SUBJECT).addAttribute(attribute);
    }

    categoryServices.get(Constants.ServiceName.RESOURCE).deleteAllAttributes();
    for (Attribute attribute : exportDtos.getResource()) {
      categoryServices.get(Constants.ServiceName.RESOURCE).addAttribute(attribute);
    }

    categoryServices.get(Constants.ServiceName.ACTION).deleteAllAttributes();
    for (Attribute attribute : exportDtos.getAction()) {
      categoryServices.get(Constants.ServiceName.ACTION).addAttribute(attribute);
    }

    abacService.deleteAllPolicies();
    for (Policy policy : exportDtos.getPolicies()) {
      abacService.addPolicy(policy);
    }
  }
}
