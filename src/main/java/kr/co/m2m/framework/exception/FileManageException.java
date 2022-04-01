package kr.co.m2m.framework.exception;

public class FileManageException extends RuntimeException {
  private static final long serialVersionUID = -6306190251854784764L;
  
  private String exCode = null;
	private Object[] args = null;

	private Throwable cause;

	public FileManageException(String exCode) {
		this.exCode = exCode;
	}

	public FileManageException(String exCode, Object[] objects, Throwable e) {
		this.exCode = exCode;
	}

	public FileManageException(String exCode, Object[] args) {
		this.exCode = exCode;
		this.args = args;
	}

	public FileManageException(String exCode, Throwable cause) {
		this.exCode = exCode;
		this.cause = cause;
	}

	public FileManageException(String exCode, Object[] args, Exception exception) {
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
