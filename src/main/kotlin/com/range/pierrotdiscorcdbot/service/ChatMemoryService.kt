package com.range.pierrotdiscorcdbot.service

import com.range.pierrotdiscorcdbot.domain.entity.ChatMemoryEntity


interface ChatMemoryService {
    fun save(content: String,isBot: Boolean): ChatMemoryEntity
    fun findLast30Chat():List<ChatMemoryEntity>


}