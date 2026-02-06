package com.range.pierrotdiscorcdbot.domain.repository

import com.range.pierrotdiscorcdbot.domain.entity.ChatMemoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ChatMemoryRepository : JpaRepository<ChatMemoryEntity, Long> {
     fun findTop30ByOrderByCreatedAtDesc(): List<ChatMemoryEntity>
}