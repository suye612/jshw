/**
 * 
 */
package com.jhsw.statistics.config;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

/**
 * @author Administrator
 *
 */
@Configuration
@MapperScan("com.jhsw.statistics.dao")
public class MybatisConfig {
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		p.setProperty("dialect", "mysql");
		p.setProperty("supportMethodsArguments", "false");
		p.setProperty("pageSizeZero", "true");
		pageHelper.setProperties(p);
		return pageHelper;
//		pagehelper:
//			  helper-dialect: mysql
//			  reasonable: true
//			  support-methods-arguments: true
//			  params: count==countSql
//			  page-size-zero: true
	}
}
