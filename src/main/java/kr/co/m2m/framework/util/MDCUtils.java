package kr.co.m2m.framework.util;

import java.util.Map;

import org.slf4j.MDC;
import org.slf4j.spi.MDCAdapter;

public class MDCUtils {
  private static MDCAdapter mdc = MDC.getMDCAdapter();

  public static final String HEADER_MAP = "HEADER_MAP";
  public static final String PARAMETER_MAP = "PARAMETER_MAP";
  public static final String TRACE_ID = "TRACE_ID";
  public static final String SERVICE_NAME = "SERVICE_NAME";
  public static final String TAG = "TAG";
  public static final String ACTION = "ACTION";
  public static final String LANG = "LANG";
  public static final String USER_INFO = "USER_INFO";
  public static final String REQUEST_URI = "REQUEST_URI";
  public static final String AGENT_DETAIL = "AGENT_DETAIL";

  public static void set(String key, String value) {
    mdc.put(key, value);
  }

  public static void setJsonValue(String key, Object value) {
    if (value != null) {
      String json = JsonUtils.toJson(value);
      mdc.put(key, json);
    }
  }

  public static String get(String key) {
    return mdc.get(key);
  }

  public static void clear() {
    MDC.clear();
  }

  public static void setErrorAttribute(Map<String, Object> errorAttribute) {
    if (errorAttribute.containsKey("path"))
      set("REQUEST_URI", (String) errorAttribute.get("path"));
  }
}
