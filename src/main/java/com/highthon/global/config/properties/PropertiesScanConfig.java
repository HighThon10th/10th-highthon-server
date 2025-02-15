package com.highthon.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackages = {
        "com.highthon.global.security.jwt.properties",
        "com.highthon.global.s3.properties"
})
public class PropertiesScanConfig {
}
