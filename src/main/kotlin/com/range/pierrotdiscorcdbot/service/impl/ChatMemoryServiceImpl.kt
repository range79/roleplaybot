package com.range.pierrotdiscorcdbot.service.impl

import com.range.pierrotdiscorcdbot.domain.entity.ChatMemoryEntity
import com.range.pierrotdiscorcdbot.domain.repository.ChatMemoryRepository
import com.range.pierrotdiscorcdbot.service.ChatMemoryService
import org.springframework.stereotype.Service

@Service
class ChatMemoryServiceImpl (
    private val chatMemoryRepository: ChatMemoryRepository
): ChatMemoryService {
    override fun save(content: String, isBot: Boolean): ChatMemoryEntity {
        val chatMemoryEntity = ChatMemoryEntity(
            id = 0,
            content = content,
            isBot = isBot,
        )
      return chatMemoryRepository.save(chatMemoryEntity)



    }

    override fun findLast30Chat(): List<ChatMemoryEntity> {
return chatMemoryRepository.findTop30ByOrderByCreatedAtDesc()
    }


}