package com.lab.user.global.config

import com.zaxxer.hikari.HikariDataSource
import net.javacrumbs.shedlock.core.LockProvider
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import javax.sql.DataSource

@EnableSchedulerLock(defaultLockAtMostFor = "15s")
@EnableScheduling
@Configuration
class SchedulerConfig {

    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "spring.datasource.shedlock")
    @Bean(name = ["shedLockDataSource"])
    fun shedLockDataSource(): DataSource = DataSourceBuilder.create()
        .type(HikariDataSource::class.java)
        .build()

    @ConditionalOnMissingBean
    @Bean(name = ["lockProvider"])
    fun lockProvider(@Qualifier("shedLockDataSource") dataSource: DataSource): LockProvider =
        JdbcTemplateLockProvider(dataSource)
}
