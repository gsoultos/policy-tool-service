package com.gsoultos.policytoolservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@ToString
public class ApiExceptionDto {
  private final Date timestamp;
  private final int status;
  private final String error;
  private final String message;
}
