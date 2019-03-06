package com.intflag.springboot.configuration;

import com.intflag.springboot.realm.TenDirRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月19日 下午6:17:13
 * @Description Shiro配置
 * @version V1.0
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 将自己的验证方式加入容器
     * @return
     */
	@Bean
	public TenDirRealm tenDirRealm() {
		TenDirRealm tenDirRealm = new TenDirRealm();
		return tenDirRealm;
	}

    /**
     * 权限管理，配置主要是Realm的管理认证
      * @return
     */
	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(tenDirRealm());
		return securityManager;
	}

	/**
	 * Filter工厂，设置对应的过滤条件和跳转条件
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 配置登录的url和登录成功的url
		shiroFilterFactoryBean.setLoginUrl("/login.html");
		shiroFilterFactoryBean.setSuccessUrl("/admin");
		// 配置访问权限
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 表示可以匿名访问
        filterChainDefinitionMap.put("/admin/sysUser/validateCode", "anon");
		filterChainDefinitionMap.put("/admin/sysUser/login", "anon");
		filterChainDefinitionMap.put("/admin/sysUser/logout", "anon");
		// 释放静态资源
		filterChainDefinitionMap.put("/admin-static/**", "anon");
		// App端请求不拦截
		filterChainDefinitionMap.put("/v1/app/**", "anon");

        // 表示需要认证才可以访问
        filterChainDefinitionMap.put("/content/**", "authc");
        // 表示需要认证才可以访问
        filterChainDefinitionMap.put("/*", "authc");
        // 表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "authc");
		filterChainDefinitionMap.put("/*.*", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

    /**
     * 加入注解的使用，不加入这个注解不生效
     * @param securityManager
     * @return
     */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			org.apache.shiro.mgt.SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}