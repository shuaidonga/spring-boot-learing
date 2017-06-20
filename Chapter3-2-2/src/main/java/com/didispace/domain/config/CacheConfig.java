package com.didispace.domain.config;

import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;
import java.util.Locale;

@Configuration
@EnableCaching
@MultipartConfig
public class CacheConfig{
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		//设置默认区域,
		slr.setDefaultLocale(Locale.CHINA);
		return slr;
	}
	@Bean 
    public MultipartConfigElement multipartConfigElement() { 
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //// 设置文件大小限制 ,超了，页面会抛出异常信息
        factory.setMaxFileSize("12MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("25MB"); 
        //Sets the directory location where files will be stored.
        //factory.setLocation("路径地址");
        return factory.createMultipartConfig(); 
    } 
}
