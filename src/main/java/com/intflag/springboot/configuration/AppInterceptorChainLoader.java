package com.intflag.springboot.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月30日 下午2:24:19
 * @Description
 * @version V1.0
 */
@Configuration
public class AppInterceptorChainLoader extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/v1/app/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "POST")
				.allowCredentials(false).maxAge(3600);
	}

}
