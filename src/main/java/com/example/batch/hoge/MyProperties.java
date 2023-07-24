package com.example.batch.hoge;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "properties.hoge")
public class MyProperties {

	private String hogehoge;
	private String hugahuga;
}
