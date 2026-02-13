package com.range.rolePlayBot.domain.repository

import com.range.rolePlayBot.domain.entity.DiscordChatMemoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DiscordChatMemoryRepository : JpaRepository<DiscordChatMemoryEntity, Long> {
     fun findTop30ByOrderByCreatedAtDesc(): List<DiscordChatMemoryEntity>
     fun findTop10ByOrderByCreatedAtDesc(): List<DiscordChatMemoryEntity>
}