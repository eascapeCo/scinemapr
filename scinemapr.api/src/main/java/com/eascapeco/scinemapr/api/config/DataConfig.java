package com.eascapeco.scinemapr.api.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.eascapeco.cinemapr")
public class DataConfig {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
}
