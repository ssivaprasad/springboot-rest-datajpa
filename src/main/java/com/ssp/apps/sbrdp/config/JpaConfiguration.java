package com.ssp.apps.sbrdp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.ssp.apps.sbrdp.dao"})
@EnableJpaRepositories
public class JpaConfiguration {

}
