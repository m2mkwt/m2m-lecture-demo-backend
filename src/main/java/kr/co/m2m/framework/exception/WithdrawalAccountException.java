package kr.co.m2m.framework.exception;

import org.springframework.security.core.AuthenticationException;

public class WithdrawalAccountException extends AuthenticationException {

	public WithdrawalAccountException(String msg) {
		super(msg);
	}

	public WithdrawalAccountException(String msg, Throwable t) {
		super(msg, t);
	}
}
