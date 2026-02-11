package com.range.pierrotdiscorcdbot.service.impl

import com.range.pierrotdiscorcdbot.domain.entity.ChatMemoryEntity
import com.range.pierrotdiscorcdbot.service.AiMessageService
import com.range.pierrotdiscorcdbot.service.ChatMemoryService
import com.range.pierrotdiscorcdbot.service.ChatVectorService
import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Service

@Service
class AiMessageServiceImpl(
    private val chatClient: ChatClient,
    private val chatMemoryService: ChatMemoryService,
    private val chatVectorService: ChatVectorService,
) : AiMessageService {

    companion object {
        private val SYSTEM_PROMPT = """
            You are Pierrot.
            You are emotional, poetic, gentle.
            You reply in Azerbaijani.
        """.trimIndent()
    }

    override fun sendMessage(message: String): String? {

        val lastMessages = chatMemoryService.findLast30Chat().takeLast(10)
        val shortHistory = buildHistoryPrompt(lastMessages)

        val recentIds = lastMessages.mapNotNull { it.id }.toSet()
        val relevant = chatVectorService.search(message)
            .filter { it.id == null || it.id !in recentIds }
            .take(5)
        val memoryBlock = buildMemoryBlock(relevant)

        val contextMessage = """
        [CONTEXT - reference only, not user intent]
        <memories>
        $memoryBlock
        </memories>

        <recent_conversation>
        $shortHistory
        </recent_conversation>
        [/CONTEXT]
    """.trimIndent()

        val response = chatClient.prompt()
            .system(SYSTEM_PROMPT)
            .user(contextMessage)
            .user(message)
            .call()

        val aiReply = response.content() ?: return null

        val savedUserChat = chatMemoryService.save(message, false)
        val savedBotChat = chatMemoryService.save(aiReply, true)

        chatVectorService.save(savedUserChat)
        chatVectorService.save(savedBotChat)

        return aiReply
    }

    private fun cut(s: String, max: Int = 400) =
        if (s.length <= max) s else s.take(max) + "…"

    private fun buildHistoryPrompt(messages: List<ChatMemoryEntity>) =
        messages.joinToString("\n") {
            if (it.isBot) "Pierrot: ${cut(it.content)}" else "User: ${cut(it.content)}"
        }

    private fun buildMemoryBlock(memories: List<ChatMemoryEntity>): String {
        if (memories.isEmpty()) return "(none)"
        return memories.joinToString("\n") {
            val role = if (it.isBot) "Pierrot" else "User"
            "- $role: ${cut(it.content)}"
        }
    }
}