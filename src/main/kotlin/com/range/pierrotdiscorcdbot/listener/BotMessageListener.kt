package com.range.pierrotdiscorcdbot.listener

import com.range.pierrotdiscorcdbot.properties.BotProperties
import com.range.pierrotdiscorcdbot.service.AiMessageService
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import kotlin.math.log

@Configuration
class BotMessageListener(
    private val botProperties: BotProperties,
    private val aiMessageService: AiMessageService
) : ListenerAdapter() {
    companion object {
        private val LOG = LoggerFactory.getLogger(BotMessageListener::class.java)

    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.message.author.isBot) return
        if (event.author.name != botProperties.ownerUsername) {
            event.message.reply("You are not ${botProperties.ownerUsername}").queue()
            return
        }
        val reply = aiMessageService.sendMessage(event.message.contentRaw)
        if (reply== null){
            event.message.reply("AI is not responding. Please try again later.").queue()
            LOG.error("AI is not responding. Please try again later.")
            return
        }
        event.message.reply(reply).queue()

    }

}