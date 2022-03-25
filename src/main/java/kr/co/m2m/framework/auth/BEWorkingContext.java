package kr.co.m2m.framework.auth;

import java.util.function.Supplier;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.SmartValidator;

@Component("BEWorkingContext")
public class BEWorkingContext implements InitializingBean {

	private static class CurrentContext {
		private String authToken;
		private BEAuthDetailModel userDetail;
		private boolean isRawResponse;
		private HttpStatus responseHttpStatus;
		private String responseReturnCode;
		private String responseMessage;
		private String[] responseSubMessages;
		private Long totalDataCount;
		private Integer dataCount;
		private String requestUri;
		private boolean isInnerCall;

		private void clear() {
			this.authToken = null;
			this.userDetail = null;
			this.isRawResponse = false;
			this.responseHttpStatus = null;
			this.responseReturnCode = null;
			this.responseMessage = null;
			this.responseSubMessages = null;
			this.totalDataCount = null;
			this.dataCount = null;
			this.requestUri = null;
			this.isInnerCall = false;
		}
	}

	private static ThreadLocal<CurrentContext> holder = ThreadLocal.withInitial(new Supplier<CurrentContext>() {
		@Override
		public CurrentContext get() {
			return new CurrentContext();
		}
	});

	private static ApplicationContext appctx;

	private static MessageSource messageSource;

	private static SmartValidator validator;

	@Autowired
	private ApplicationContext instanceAppCtx;

	@Autowired
	private MessageSource instanceMsgSrc;

	@Override
	public void afterPropertiesSet() throws Exception {
		BEWorkingContext.appctx = this.instanceAppCtx;
		BEWorkingContext.messageSource = this.instanceMsgSrc;

	}

	public static ApplicationContext getApplicationContext() {
		return appctx;
	}

	public static void setApplicationContext(ApplicationContext appCtx) {
		appctx = appCtx;
	}

	public static MessageSource getMessageSource() {
		return messageSource;
	}

	public static void setMessageSource(MessageSource ms) {
		messageSource = ms;
	}

	public static SmartValidator getValidator() {
		return validator;
	}

	public static void setValidator(SmartValidator validator) {
		BEWorkingContext.validator = validator;
	}

	public static void setAuthDetail(BEAuthDetailModel userDetail) {
		holder.get().userDetail = userDetail;
	}

	public static void setAuthToken(String authToken) {
		holder.get().authToken = authToken;
	}

	public static void setResponseHttpStatus(HttpStatus httpStatus) {
		holder.get().responseHttpStatus = httpStatus;
	}

	public static HttpStatus getResponseHttpStatus() {
		return holder.get().responseHttpStatus;
	}

	public static String getAuthToken() {
		return holder.get().authToken;
	}

	public static void setResponseReturnCode(String returnCode) {
		holder.get().responseReturnCode = returnCode;
	}

	public static String getResponseReturnCode() {
		return holder.get().responseReturnCode;
	}

	public static void setResponseMessage(String message, String[] subMessages) {
		CurrentContext ctx;

		ctx = holder.get();
		ctx.responseMessage = message;
		ctx.responseSubMessages = subMessages;
	}

	public static void setResponseMessage(String message) {
		setResponseMessage(message, null);
	}

	public static String getMessage() {
		return holder.get().responseMessage;
	}

	public static String[] getSubMessages() {
		return holder.get().responseSubMessages;
	}

	public static void setRawResponse(boolean isRawResponse) {
		holder.get().isRawResponse = isRawResponse;
	}

	public static boolean isRawResponse() {
		return holder.get().isRawResponse;
	}

	public static void setRequestUri(String uri) {
		holder.get().requestUri = uri;
	}

	public static String getRequestUri() {
		return holder.get().requestUri;
	}

	public static void clear() {
		holder.get().clear();
	}

}
