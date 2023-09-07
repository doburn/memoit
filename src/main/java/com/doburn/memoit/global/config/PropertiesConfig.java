package com.doburn.memoit.global.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.doburn.memoit.global.properties.GoogleProperties;

@Configuration
@EnableConfigurationProperties(GoogleProperties.class)
public class PropertiesConfig {
}
