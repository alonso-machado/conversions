package com.alonso.conversion;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableSpringDataWebSupport
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableJpaAuditing
@EntityScan(basePackages = {"com.alonso.conversion"})
@EnableJpaRepositories("com.alonso.conversion")
public class AppConfig {

}
