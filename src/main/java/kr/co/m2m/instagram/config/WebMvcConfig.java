package kr.co.m2m.instagram.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import kr.co.m2m.framework.auth.BEWorkingContext;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private ApplicationContext applicationContext;

//	@Autowired
//	private BECommonInterceptor beInterceptor;

	@PostConstruct
	public void initialize() {
		BEWorkingContext.setApplicationContext(this.applicationContext);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/main");
		// registry.addViewController("/").setViewName("/main");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
//		registry.addInterceptor(this.beInterceptor).addPathPatterns("/**");
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/message");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(20);
		return messageSource;
	}

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

}
