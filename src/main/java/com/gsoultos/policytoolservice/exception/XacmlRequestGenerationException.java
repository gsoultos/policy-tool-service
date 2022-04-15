package com.gsoultos.policytoolservice.exception;

import javax.ws.rs.InternalServerErrorException;

public class XacmlRequestGenerationException extends InternalServerErrorException {
  public XacmlRequestGenerationException(String message) {
    super(message);
  }
}
