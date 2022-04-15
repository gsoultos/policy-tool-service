package com.gsoultos.policytoolservice.service;

import com.gsoultos.policytoolservice.common.Constants;
import org.ow2.authzforce.sdk.core.schema.category.ActionCategory;
import org.springframework.stereotype.Service;

@Service(Constants.ServiceName.ACTION)
public class ActionService extends CategoryTemplate {

  public ActionService() {
    super(new ActionCategory());
  }
}
