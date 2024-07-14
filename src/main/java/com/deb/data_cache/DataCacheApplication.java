package com.deb.data_cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Date;

@SpringBootApplication
@EnableCaching
public class DataCacheApplication {

	private static final Logger log = LoggerFactory.getLogger(DataCacheApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DataCacheApplication.class, args);
		log.info("Application Started at :{}", new Date());
	}

}
