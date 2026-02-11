package com.range.pierrotdiscorcdbot.service.impl

import com.range.pierrotdiscorcdbot.domain.entity.ChatMemoryEntity
import com.range.pierrotdiscorcdbot.service.ChatVectorService
import org.springframework.ai.document.Document
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.stereotype.Service

@Service
class ChatVectorServiceImpl(
    private val vectorStore: VectorStore
) : ChatVectorService {

    override fun save(chatMemoryEntity: ChatMemoryEntity) {
        val metadata: MutableMap<String, out Any?> = mutableMapOf(
            "messageId" to (chatMemoryEntity.id),
            "isBot" to chatMemoryEntity.isBot
        )

        val doc = Document(
            (chatMemoryEntity.id ?: -1L).toString(),
            chatMemoryEntity.content,
            metadata
        )

        vectorStore.add(listOf(doc))
    }
}
