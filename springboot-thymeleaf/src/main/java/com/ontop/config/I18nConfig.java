package com.ontop.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class I18nConfig {
	/*
	 * 默认解析器：LocaleResolver 用于设置当前会话的默认的国际化语言
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	/*
	 * 默认拦截器：LocaleChangeInterceptor 指定切换国际化语言的参数名。
	 * 例如?lang=zh_CN 表示读取国际化文件messages_zh_CN.properties
	 */
	@Bean
	public WebMvcConfigurer localeInterceptor() {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
				localeChangeInterceptor.setParamName("lang");
				registry.addInterceptor(localeChangeInterceptor);
			}
		};
	}
}
