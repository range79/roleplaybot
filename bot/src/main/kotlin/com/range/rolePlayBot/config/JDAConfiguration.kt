package com.range.rolePlayBot.config

import com.range.rolePlayBot.listeners.slash.SlashCommandDefinition
import com.range.rolePlayBot.properties.BotProperties

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.requests.GatewayIntent
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JDAConfiguration(
    private val botProperties: BotProperties,
    private val listeners: List<EventListener>,
    private val slashDefs: List<SlashCommandDefinition>
) {
    companion object {
        private val LOG = LoggerFactory.getLogger(JDAConfiguration::class.java)
    }

    @Bean
    fun jda(): JDA {
        val builder = JDABuilder.createDefault(botProperties.token)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)

        listeners.forEach { builder.addEventListeners(it) }
        val jda = builder.build()
        jda.awaitReady()
        jda.updateCommands()
            .addCommands(slashDefs.map { it.data() })
            .queue(
                { LOG.info("Slash commands registered: ${slashDefs.size}") },
                { e -> LOG.error("Slash command register failed", e) }
            )

        return jda
    }
}
