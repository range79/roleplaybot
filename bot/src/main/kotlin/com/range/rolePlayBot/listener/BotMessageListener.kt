package com.range.rolePlayBot.listener

import com.range.rolePlayBot.properties.BotProperties
import com.range.rolePlayBot.service.AiMessageService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class BotMessageListener(
    private val botProperties: BotProperties,
    private val aiMessageService: AiMessageService,
    private val appScope: CoroutineScope
) : ListenerAdapter() {

    companion object {
        private val LOG = LoggerFactory.getLogger(BotMessageListener::class.java)
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.author.isBot) return
        if (event.author.name != botProperties.ownerUsername) {
            event.message.reply("You are not ${botProperties.ownerUsername}").queue()
            return
        }

        appScope.launch {
            try {
                val reply = aiMessageService.sendMessage(event.message.contentRaw)
                event.message.reply(reply ?: "AI is not responding. Please try again later.").queue()
            } catch (e: Exception) {
                LOG.error("AI error", e)
                event.message.reply("Internal error occurred.").queue()
            }
        }
    }
}
