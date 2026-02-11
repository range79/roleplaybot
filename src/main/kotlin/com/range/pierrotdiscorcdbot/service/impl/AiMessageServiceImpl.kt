package com.range.pierrotdiscorcdbot.service.impl

import com.range.pierrotdiscorcdbot.domain.entity.ChatMemoryEntity
import com.range.pierrotdiscorcdbot.service.AiMessageService
import com.range.pierrotdiscorcdbot.service.ChatMemoryService
import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Service

@Service
class AiMessageServiceImpl
    (
    private val chatClient: ChatClient,
    private val chatMemoryService: ChatMemoryService,
) : AiMessageService {

    companion object {
        private val PROMPT = """
        You are Pierrot.
        You are emotional, poetic, gentle.
        You reply in Azerbaijani.
    """.trimIndent()
    }


    override fun sendMessage(message: String): String? {

        val lastMessages = chatMemoryService.findLast30Chat().reversed()

        val history = buildHistoryPrompt(lastMessages)

        val fullSystemPrompt = """
        $PROMPT

        Previous conversation:
        $history
    """.trimIndent()

        val response = chatClient.prompt()
            .system(fullSystemPrompt)
            .user(message)
            .call()

        val aiReply = response.content() ?: return null

        chatMemoryService.save(message, false)
        chatMemoryService.save(aiReply, true)

        return aiReply
    }

    private fun buildHistoryPrompt(messages: List<ChatMemoryEntity>): String {
        return messages.joinToString("\n") {
            if (it.isBot) {
                "Pierrot: ${it.content}"
            } else {
                "User: ${it.content}"
            }
        }
    }


}
