package com.range.pierrotdiscorcdbot

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
@ConfigurationPropertiesScan
@SpringBootApplication
class PierrotDiscorcdBotApplication

fun main(args: Array<String>) {
    runApplication<PierrotDiscorcdBotApplication>(*args)
}
