package com.range.rolePlayBot.listeners.mention

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
        if (!event.message.mentions.isMentioned(event.jda.selfUser)) return
        if (event.author.isBot) return
        if (event.author.name != botProperties.clientUsername) {
            event.message.reply("You are not ${botProperties.clientUsername}").queue()
            return
        }

        val prompt = event.message.contentRaw
            .replace(event.jda.selfUser.asMention, "")
            .trim()

        if (prompt.isBlank()) {
            event.message.reply("Write something after mentioning me 🙂").queue()
            return
        }
        event.channel.sendTyping().queue()

        appScope.launch {
            try {
                val reply = aiMessageService.sendMessage(prompt)
                    ?: "AI is not responding. Please try again later."

                event.message.reply(reply).queue()
            } catch (e: Exception) {
                LOG.error("AI error", e)
                event.message.reply("Internal error occurred.").queue()
            }
        }

    }
}