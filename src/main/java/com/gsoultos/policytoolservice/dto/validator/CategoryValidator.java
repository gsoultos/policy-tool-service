package com.gsoultos.policytoolservice.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoryConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryValidator {
  String message() default "Invalid category.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
