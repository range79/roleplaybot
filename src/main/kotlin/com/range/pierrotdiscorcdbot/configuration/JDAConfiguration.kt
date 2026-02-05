package com.range.pierrotdiscorcdbot.configuration

import com.range.pierrotdiscorcdbot.properties.BotProperties
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JDAConfiguration(
    private val botProperties: BotProperties
) {
    @Bean
    fun config(): JDA {
        val jda = JDABuilder.createDefault(botProperties.token).build()
        return jda
    }
}