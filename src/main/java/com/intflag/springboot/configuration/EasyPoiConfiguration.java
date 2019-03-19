package com.intflag.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.BeanNameViewResolver;

/**
 * @author liugx  QQ:1598749808
 * @version V1.0
 * @date 2019-03-19 22:10
 * @Description EasyPoi配置
 */
@Configuration
public class EasyPoiConfiguration {
    @Bean
    public BeanNameViewResolver getBeanNameViewResolver() {
        BeanNameViewResolver view = new BeanNameViewResolver();
        view.setOrder(-100);
        return view;
    }
}
