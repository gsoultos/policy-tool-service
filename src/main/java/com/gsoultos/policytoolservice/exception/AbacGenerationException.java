package com.gsoultos.policytoolservice.exception;

import javax.ws.rs.InternalServerErrorException;

public class AbacGenerationException extends InternalServerErrorException {
  public AbacGenerationException(String message) {
    super(message);
  }
}
