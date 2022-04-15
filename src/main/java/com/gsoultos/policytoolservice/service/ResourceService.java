package com.gsoultos.policytoolservice.service;

import com.gsoultos.policytoolservice.common.Constants;
import org.ow2.authzforce.sdk.core.schema.category.ResourceCategory;
import org.springframework.stereotype.Service;

@Service(Constants.ServiceName.RESOURCE)
public class ResourceService extends CategoryTemplate {
  public ResourceService() {
    super(new ResourceCategory());
  }
}
