package com.range.rolePlayBot.service

import com.range.rolePlayBot.domain.entity.ChatMemoryEntity


interface ChatMemoryService {
    fun save(content: String,isBot: Boolean): ChatMemoryEntity
    fun findLast30Chat():List<ChatMemoryEntity>


}