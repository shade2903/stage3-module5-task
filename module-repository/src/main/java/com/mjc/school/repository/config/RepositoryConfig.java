package com.mjc.school.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan("com.mjc.school.*")
@EntityScan(basePackages = "com.mjc.school.repository.model")
public class RepositoryConfig {
}
