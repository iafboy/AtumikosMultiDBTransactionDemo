package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.demo.datasource.DBConfig1;
import com.demo.datasource.DBConfig2;

/**
 *
 */
@SpringBootApplication
@EnableConfigurationProperties(value = { DBConfig1.class, DBConfig2.class })

@MapperScan(basePackages = { "com.demo.mapper" })

//@EnableAutoConfiguration
//@EnableAsync
//@EnableCaching
public class App {
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
