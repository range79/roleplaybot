package com.range.rolePlayBot.service.impl

import com.range.rolePlayBot.domain.entity.ChatMemoryEntity
import com.range.rolePlayBot.service.ChatVectorService
import org.springframework.ai.document.Document
import org.springframework.ai.vectorstore.SearchRequest
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChatVectorServiceImpl(
    private val vectorStore: VectorStore
) : ChatVectorService {
    override fun save(chatMemoryEntity: ChatMemoryEntity) {
        val id = requireNotNull(chatMemoryEntity.id) { "ChatMemoryEntity.id null olamaz (save'den sonra gelmeli)" }

        val metadata: MutableMap<String, Any> = mutableMapOf(
            "messageId" to id,
            "isBot" to chatMemoryEntity.isBot
        )

        val doc = Document(
            id.toString(),
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
                id = (doc.metadata["messageId"] as? UUID),
                content = doc.text ?: "",
                isBot = doc.metadata["isBot"] as? Boolean ?: false
            )
        }
    }
}
