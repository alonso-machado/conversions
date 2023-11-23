package com.alonso.conversion;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;


@Configuration
@EnableWebFlux
@EnableAspectJAutoProxy
@EnableJpaAuditing
@EntityScan(basePackages = {"com.alonso.conversion"})
@EnableJpaRepositories("com.alonso.conversion")
@EnableCaching
@EnableEncryptableProperties
public class AppConfig {

}
