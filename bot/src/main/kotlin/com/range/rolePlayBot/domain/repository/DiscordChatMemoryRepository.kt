package com.range.rolePlayBot.domain.repository

import com.range.rolePlayBot.domain.entity.ChatMemoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DiscordChatMemoryRepository : JpaRepository<ChatMemoryEntity, Long> {
     fun findTop30ByOrderByCreatedAtDesc(): List<ChatMemoryEntity>
}