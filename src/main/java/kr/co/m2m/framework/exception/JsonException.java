package kr.co.m2m.framework.exception;

public class JsonException extends RuntimeException {

  private static final long serialVersionUID = 7543113073692662093L;

  public JsonException(String message, Throwable cause) {
    super(message, cause);
  }
}
