package com.range.pierrotdiscorcdbot.service

import org.springframework.ai.chat.client.ChatClient

import org.springframework.stereotype.Service

@Service
class AiMessageService(
    private val chatClient: ChatClient,
    private val chatMemoryService: ChatMemoryService,
) {

    private val SYSTEM_PROMPT = """
        You are Pierrot.
        You are emotional, poetic, gentle.
        You reply in Turkish.
    """.trimIndent()

    fun sendMessage(message: String): String? {

        val response = chatClient.prompt()
            .system(SYSTEM_PROMPT)
            .user(message)
            .call()
        val aiReply = response.content() ?: return null

        chatMemoryService.save(message,false)

        chatMemoryService.save(aireply,true)
        return aiReply

    }
}
