package com.gsoultos.policytoolservice.service;

import com.gsoultos.policytoolservice.common.Constants;
import org.ow2.authzforce.sdk.core.schema.category.SubjectCategory;
import org.springframework.stereotype.Service;

@Service(Constants.ServiceName.SUBJECT)
public class SubjectService extends CategoryTemplate {
  public SubjectService() {
    super(new SubjectCategory());
  }
}
