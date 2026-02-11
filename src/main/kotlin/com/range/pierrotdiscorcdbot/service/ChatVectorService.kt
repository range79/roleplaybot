package com.range.pierrotdiscorcdbot.service

import com.range.pierrotdiscorcdbot.domain.entity.ChatMemoryEntity

interface ChatVectorService {
    fun save(chatMemoryEntity: ChatMemoryEntity)
}