package com.range.rolePlayBot.service

import com.range.rolePlayBot.domain.entity.DiscordChatMemoryEntity

interface ChatVectorService {
    fun save(discordChatMemoryEntity: DiscordChatMemoryEntity)
    fun search(text: String): List<DiscordChatMemoryEntity>
}