package com.range.rolePlayBot.service

import com.range.rolePlayBot.domain.entity.DiscordChatMemoryEntity


interface ChatMemoryService {
    fun save(content: String,isBot: Boolean): DiscordChatMemoryEntity
    fun findLast30Chat():List<DiscordChatMemoryEntity>


}