package com.intflag.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.intflag.springboot.mapper.*")
/**
 * 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
 */
@EnableTransactionManagement
@SpringBootApplication
public class BttcPmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BttcPmsApplication.class, args);
	}
	
}
