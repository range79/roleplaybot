package com.range.pierrotdiscorcdbot.service

import com.range.pierrotdiscorcdbot.properties.BotProperties
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
        if(event.author.name!=botProperties.ownerUsername) {
            event.message.reply("You are not ${botProperties.ownerUsername}")
        }
        aiMessageService.sendMessage(event.message)






    }

}