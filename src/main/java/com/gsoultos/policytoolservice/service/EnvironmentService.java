package com.gsoultos.policytoolservice.service;

import com.gsoultos.policytoolservice.common.Constants;
import org.ow2.authzforce.sdk.core.schema.category.EnvironmentCategory;
import org.springframework.stereotype.Service;

@Service(Constants.ServiceName.ENVIRONMENT)
public class EnvironmentService extends CategoryTemplate {

  public EnvironmentService() {
    super(new EnvironmentCategory());
  }
}
