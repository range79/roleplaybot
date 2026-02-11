package com.range.pierrotdiscorcdbot.service.impl

import com.range.pierrotdiscorcdbot.domain.entity.ChatMemoryEntity
import com.range.pierrotdiscorcdbot.service.ChatVectorService
import org.springframework.ai.document.Document
import org.springframework.ai.vectorstore.SearchRequest
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
            (chatMemoryEntity.id).toString(),
            chatMemoryEntity.content,
            metadata
        )

        vectorStore.add(listOf(doc))
    }

    override fun search(text: String): List<ChatMemoryEntity> {

        val request = SearchRequest.builder()
            .query(text)
            .topK(5)
            .build()

        val results = vectorStore.similaritySearch(request)

        return results.map { doc ->

            ChatMemoryEntity(
                id = (doc.metadata["messageId"] as? Number)?.toLong(),
                content = doc.text ?: "",
                isBot = doc.metadata["isBot"] as? Boolean ?: false
            )
        }
    }
}
