package com.range.pierrotdiscorcdbot.service

import com.range.pierrotdiscorcdbot.domain.entity.ChatMemoryEntity
import com.range.pierrotdiscorcdbot.domain.repository.ChatMemoryRepository
import org.springframework.stereotype.Service


interface ChatMemoryService {
    fun save(content: String,isBot: Boolean): ChatMemoryEntity
    fun findLast30Chat():List<ChatMemoryEntity>


}