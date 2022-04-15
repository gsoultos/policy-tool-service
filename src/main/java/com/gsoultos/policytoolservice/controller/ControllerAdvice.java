//package com.gsoultos.policytoolservice.controller;
//
//import com.gsoultos.policytoolservice.dto.ApiExceptionDto;
//import com.gsoultos.policytoolservice.exception.XacmlRequestGenerationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import javax.validation.ConstraintViolationException;
//import java.util.Date;
//
//@org.springframework.web.bind.annotation.ControllerAdvice
//public class ControllerAdvice {
//  @ExceptionHandler(ConstraintViolationException.class)
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  ResponseEntity<ApiExceptionDto> constraintViolationExceptionHandler(ConstraintViolationException e) {
//    return new ResponseEntity<>(
//        new ApiExceptionDto(
//            new Date(),
//            HttpStatus.BAD_REQUEST.value(),
//            HttpStatus.BAD_REQUEST.getReasonPhrase(),
//            "Invalid request."),
//        HttpStatus.BAD_REQUEST);
//  }
//
//  @ExceptionHandler({HttpMessageNotReadableException.class, IllegalArgumentException.class})
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  ResponseEntity<ApiExceptionDto> messageNotReadableExceptionHandler(Exception e) {
//
//    return new ResponseEntity<>(
//        new ApiExceptionDto(
//            new Date(),
//            HttpStatus.BAD_REQUEST.value(),
//            HttpStatus.BAD_REQUEST.getReasonPhrase(),
//            "Invalid data format."),
//        HttpStatus.BAD_REQUEST);
//  }
//
//  @ExceptionHandler(MissingServletRequestParameterException.class)
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  ResponseEntity<ApiExceptionDto> missingRequestParameterExceptionHandler(
//      MissingServletRequestParameterException e) {
//
//    return new ResponseEntity<>(
//        new ApiExceptionDto(
//            new Date(),
//            HttpStatus.BAD_REQUEST.value(),
//            HttpStatus.BAD_REQUEST.getReasonPhrase(),
//            "Invalid request."),
//        HttpStatus.BAD_REQUEST);
//  }
//
//  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//  ResponseEntity<ApiExceptionDto> methodNotSupportedExceptionHandler(
//      HttpRequestMethodNotSupportedException e) {
//
//    return new ResponseEntity<>(
//        new ApiExceptionDto(
//            new Date(),
//            HttpStatus.METHOD_NOT_ALLOWED.value(),
//            HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),
//            "Request is not allowed."),
//        HttpStatus.METHOD_NOT_ALLOWED);
//  }
//
//  @ExceptionHandler({XacmlRequestGenerationException.class})
//  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//  ResponseEntity<ApiExceptionDto> xacmlSdkExceptionHandler(XacmlRequestGenerationException e) {
//
//    return new ResponseEntity<>(
//        new ApiExceptionDto(
//            new Date(),
//            HttpStatus.INTERNAL_SERVER_ERROR.value(),
//            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
//            e.getMessage()),
//        HttpStatus.INTERNAL_SERVER_ERROR);
//  }
//
//  @ExceptionHandler(Exception.class)
//  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//  ResponseEntity<ApiExceptionDto> exceptionHandler(Exception e) {
//
//    return new ResponseEntity<>(
//        new ApiExceptionDto(
//            new Date(),
//            HttpStatus.INTERNAL_SERVER_ERROR.value(),
//            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
//            "Unknown error."),
//        HttpStatus.INTERNAL_SERVER_ERROR);
//  }
//}
