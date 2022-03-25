package kr.co.m2m.instagram.common.exception;

@SuppressWarnings("serial")
public class BEUnauthorizedException extends RuntimeException {

	public static final int UNAUTHORIZED_HTTP_STATUS_CODE = 401;
	public static final String UNAUTHORIZED_RETURN_CODE = "UNAUTHORIZED";

	protected String returnCode;
	protected Object[] messageArgs;

	public BEUnauthorizedException(String returnCode, String message, Throwable cause, Object... messageArgs) {
		super(message, cause);
		this.returnCode = returnCode;
		this.messageArgs = messageArgs;
	}

	public BEUnauthorizedException(String returnCode, String message, Object... messageArgs) {
		this(returnCode, message, null, messageArgs);
	}

	public BEUnauthorizedException(String returnCode, String message, Throwable cause) {
		this(returnCode, message, cause, (Object[]) null);
	}

	public BEUnauthorizedException(String returnCode, String message) {
		this(returnCode, message, (Throwable) null, (Object[]) null);
	}

	public int getHttpStatusCode() {
		return UNAUTHORIZED_HTTP_STATUS_CODE;
	}

	public String getReturnCode() {
		return this.returnCode;
	}

	public Object[] getMessageArgs() {
		return this.messageArgs;
	}

	@Override
	public String getLocalizedMessage() {
		return "[" + this.getHttpStatusCode() + "-" + this.getReturnCode() + "]" + this.getMessage();
	}
}