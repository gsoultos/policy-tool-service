package com.gsoultos.policytoolservice.dto.validator;

import com.gsoultos.policytoolservice.common.Constants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryConstraintValidator implements ConstraintValidator<CategoryValidator, String> {
  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    try {
      Constants.CategoryName.valueOf(s);
      return true;
    } catch (Exception ignored) {
      return false;
    }
  }
}
