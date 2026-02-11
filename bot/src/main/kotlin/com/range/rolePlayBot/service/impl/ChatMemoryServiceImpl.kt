package com.range.rolePlayBot.service.impl
    
    import com.range.rolePlayBot.domain.entity.ChatMemoryEntity
    import com.range.rolePlayBot.domain.repository.DiscordChatMemoryRepository
    import com.range.rolePlayBot.service.ChatMemoryService
    import org.springframework.stereotype.Service
    
    @Service
    class ChatMemoryServiceImpl(
        private val discordChatMemoryRepository: DiscordChatMemoryRepository
    ) : ChatMemoryService {
        override fun save(content: String, isBot: Boolean): ChatMemoryEntity {
            val chatMemoryEntity = ChatMemoryEntity(
                content = content,
                isBot = isBot,
            )
            return discordChatMemoryRepository.save(chatMemoryEntity)
    
    
        }
    
        override fun findLast30Chat(): List<ChatMemoryEntity> {
            return discordChatMemoryRepository.findTop30ByOrderByCreatedAtDesc()
        }
    
    
    }