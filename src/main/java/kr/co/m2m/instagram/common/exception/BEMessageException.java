package kr.co.m2m.instagram.common.exception;

@SuppressWarnings("serial")
public class BEMessageException extends RuntimeException {

	public static final int DEFAULT_HTTP_STATUS_CODE = 500;
	public static final String DEFAULT_RETURN_CODE = "SERVER_ERROR";

	public static final int INVALID_HTTP_STATUS_CODE = 422;
	public static final String INVALID_INPUT_RETURN_CODE = "INVALID_INPUT";

	public static final int UNAUTHORIZED_HTTP_STATUS_CODE = 401;
	public static final String UNAUTHORIZED_RETURN_CODE = "UNAUTHORIZED";

	protected int httpStatusCode;
	protected String returnCode;
	protected Object[] messageArgs;

	public BEMessageException(int httpStatusCode, String returnCode, String message, Throwable cause, Object... messageArgs) {
		super(message, cause);
		this.httpStatusCode = httpStatusCode;
		this.returnCode = returnCode;
		this.messageArgs = messageArgs;
	}

	public BEMessageException(int httpStatusCode, String returnCode, String message, Object... messageArgs) {
		this(httpStatusCode, returnCode, message, null, messageArgs);
	}

	public BEMessageException(int httpStatusCode, String returnCode, String message) {
		this(httpStatusCode, returnCode, message, (Throwable) null, (Object[]) null);
	}

	public BEMessageException(String returnCode, String message, Throwable cause) {
		this(DEFAULT_HTTP_STATUS_CODE, returnCode, message, cause, (Object[]) null);
	}

	public BEMessageException(String returnCode, String message) {
		this(DEFAULT_HTTP_STATUS_CODE, returnCode, message, (Throwable) null, (Object[]) null);
	}

	public BEMessageException(String message, Throwable cause, Object... msgArgs) {
		this(DEFAULT_HTTP_STATUS_CODE, DEFAULT_RETURN_CODE, message, cause, msgArgs);
	}

	public BEMessageException(String message, Throwable cause) {
		this(DEFAULT_HTTP_STATUS_CODE, DEFAULT_RETURN_CODE, message, cause, (Object[]) null);
	}

	public BEMessageException(String message) {
		this(DEFAULT_HTTP_STATUS_CODE, DEFAULT_RETURN_CODE, message, (Throwable) null, (Object[]) null);
	}

	public int getHttpStatusCode() {
		return this.httpStatusCode;
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