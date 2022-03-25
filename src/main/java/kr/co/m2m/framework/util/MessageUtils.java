package kr.co.m2m.framework.util;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.support.MessageSourceSupport;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageUtils {

	@Resource(name = "messageSource")
	private MessageSourceSupport source;

	static MessageSourceSupport messageSource;

	@PostConstruct
	public void initialize() {
		messageSource = this.source;
		log.info("MessageUtils initialize.... source : {}", this.source);
	}

	public static AbstractMessageSource getMessageSource() {
		return (AbstractMessageSource) messageSource;
	}

	public void setSource(MessageSourceSupport source) {
		this.source = source;
	}

	public static Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}

	public static String getMessage(String code) {
		log.debug("getMessageSource() : {}", getMessageSource());
		return getMessageSource().getMessage(code, null, getLocale());
	}

	public static String getMessage(String code, Object[] args) {
		return getMessageSource().getMessage(code, args, getLocale());
	}
}
