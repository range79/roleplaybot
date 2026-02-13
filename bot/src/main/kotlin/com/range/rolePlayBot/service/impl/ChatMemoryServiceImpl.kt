package com.range.rolePlayBot.service.impl
    
    import com.range.rolePlayBot.domain.entity.DiscordChatMemoryEntity
    import com.range.rolePlayBot.domain.repository.DiscordChatMemoryRepository
    import com.range.rolePlayBot.service.ChatMemoryService
    import org.springframework.stereotype.Service
    
    @Service
    class ChatMemoryServiceImpl(
        private val discordChatMemoryRepository: DiscordChatMemoryRepository
    ) : ChatMemoryService {
        override fun save(content: String, isBot: Boolean): DiscordChatMemoryEntity {
            val discordChatMemoryEntity = DiscordChatMemoryEntity(
                content = content,
                isBot = isBot,
            )
            return discordChatMemoryRepository.save(discordChatMemoryEntity)
    
    
        }
    
        override fun findLast30Chat(): List<DiscordChatMemoryEntity> {
            return discordChatMemoryRepository.findTop30ByOrderByCreatedAtDesc()
        }
    
    
    }