package com.gsoultos.policytoolservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.Policy;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.Attribute;
import java.util.List;

@AllArgsConstructor
@Getter
public class ExportDto {
  private final List<Attribute> subject;
  private final List<Attribute> resource;
  private final List<Attribute> action;
  private final List<Policy> policies;
}
