package com.range.rolePlayBot.service.impl

import com.range.rolePlayBot.domain.repository.DiscordChatMemoryRepository
import com.range.rolePlayBot.enums.Mood
import com.range.rolePlayBot.service.MoodService
import org.springframework.stereotype.Service

@Service
class MoodServiceImpl(
    private val discordChatMemoryRepository: DiscordChatMemoryRepository
): MoodService {
    override fun mood(): Mood {
        discordChatMemoryRepository.findTop30ByOrderByCreatedAtDesc()
    }

    override fun makeHappy(): String {
        TODO("Not yet implemented")
    }

}
