package com.botianzu.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * mybatisplus 配置类
 * @author 刘彦昌
 *
 */
@Configuration
public class MyBatisPlusConfig {

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
}
