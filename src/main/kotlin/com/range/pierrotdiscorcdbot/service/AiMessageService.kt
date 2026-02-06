package com.range.pierrotdiscorcdbot.service

import com.range.pierrotdiscorcdbot.domain.repository.ChatMemoryRepository
import org.springframework.ai.chat.client.ChatClient

import org.springframework.stereotype.Service

@Service
class AiMessageService(
    private val chatClient: ChatClient,
    private val chatMemoryRepository: ChatMemoryRepository
) {

    private val SYSTEM_PROMPT = """
        You are Pierrot.
        You are emotional, poetic, gentle.
        You reply in Turkish.
    """.trimIndent()

    fun sendMessage(message: String): String {

        val prompt = chatClient.prompt()
            .system(SYSTEM_PROMPT)
            .user(message)
            .call()

        return prompt.content()?:"An unknown error occurred write to range79 lol"
    }
}
