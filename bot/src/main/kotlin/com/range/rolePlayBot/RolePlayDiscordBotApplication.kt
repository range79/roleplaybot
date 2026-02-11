package com.range.rolePlayBot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
@ConfigurationPropertiesScan
@SpringBootApplication
class PierrotDiscordBotApplication

fun main(args: Array<String>) {
    runApplication<PierrotDiscordBotApplication>(*args)
}
