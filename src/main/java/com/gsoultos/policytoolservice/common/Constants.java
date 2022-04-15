package com.gsoultos.policytoolservice.common;

public class Constants {
  public static final class ServiceName {
    public static final String ACTION = "act";
    public static final String ENVIRONMENT = "env";
    public static final String RESOURCE = "rsc";
    public static final String SUBJECT = "subj";
    public static final String REQUEST = "req";
    public static final String PROJECT = "proj";
    public static final String ABAC = "abac";
  }

  public enum CategoryName {
    act,
    env,
    rsc,
    subj
  }
}
