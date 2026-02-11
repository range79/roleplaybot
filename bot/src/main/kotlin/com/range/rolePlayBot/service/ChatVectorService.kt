package com.range.rolePlayBot.service

import com.range.rolePlayBot.domain.entity.ChatMemoryEntity

interface ChatVectorService {
    fun save(chatMemoryEntity: ChatMemoryEntity)
    fun search(text: String): List<ChatMemoryEntity>
}