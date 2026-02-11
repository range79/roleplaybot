package com.range.rolePlayBot.config

import com.range.rolePlayBot.listener.BotMessageListener
import com.range.rolePlayBot.properties.BotProperties
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JDAConfiguration(
    private val botProperties: BotProperties,
    private val botMessageListener: BotMessageListener
) {

    @Bean
    fun jda(): JDA {
        return JDABuilder.createDefault(botProperties.token)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .addEventListeners(botMessageListener)
            .build()
    }
}
