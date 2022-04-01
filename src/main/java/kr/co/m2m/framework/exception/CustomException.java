package kr.co.m2m.framework.exception;

import kr.co.m2m.framework.util.MessageUtils;

public class CustomException extends RuntimeException {
  private static final long serialVersionUID = 2568644825664496168L;
  
  private String exCode = null;
	private Object[] args = null;

	private Throwable cause;

	public CustomException(String exCode) {
		super(MessageUtils.getMessage(exCode));

		this.exCode = exCode;
	}

	public CustomException(String exCode, Object[] objects, Throwable e) {
		super(MessageUtils.getMessage(exCode), e);

		this.exCode = exCode;
	}

	public CustomException(String exCode, Object[] args) {
		super(MessageUtils.getMessage(exCode));

		this.exCode = exCode;
		this.args = args;
	}

	public CustomException(String exCode, Throwable cause) {
		super(MessageUtils.getMessage(exCode), cause);

		this.exCode = exCode;
		this.cause = cause;
	}

	public CustomException(String exCode, Object[] args, Exception exception) {
		super(MessageUtils.getMessage(exCode), exception);

		this.exCode = exCode;
		this.cause = exception;
		this.args = args;
	}

	public String getExCode() {
		return this.exCode;
	}

	@Override
	public Throwable getCause() {
		return cause;
	}

	public Object[] getArgs() {
		return args;
	}
}
