package com.range.pierrotdiscorcdbot.listener

import com.range.pierrotdiscorcdbot.properties.BotProperties
import com.range.pierrotdiscorcdbot.service.AiMessageService
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.context.annotation.Configuration

@Configuration
class BotMessageListener(
    private val botProperties: BotProperties,
    private val aiMessageService: AiMessageService
) : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.message.author.isBot) return
        if (event.author.name != botProperties.ownerUsername) {
            event.message.reply("You are not ${botProperties.ownerUsername}")
            return
        }
        val reply = aiMessageService.sendMessage(event.message.contentRaw)
        event.message.reply(reply).queue()

    }

}