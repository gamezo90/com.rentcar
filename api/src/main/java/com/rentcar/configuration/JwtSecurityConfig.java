package com.rentcar.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jwt")
//@Profile("dev")
@Data
public class JwtSecurityConfig {

    private String secret;

    private Integer expiration;
}
