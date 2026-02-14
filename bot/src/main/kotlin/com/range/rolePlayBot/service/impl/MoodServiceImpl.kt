package com.range.rolePlayBot.service.impl

import com.range.rolePlayBot.domain.repository.DiscordChatMemoryRepository
import com.range.rolePlayBot.enums.Mood
import com.range.rolePlayBot.service.MoodService
import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Service

@Service
class MoodServiceImpl(
    private val chatClient: ChatClient,
    private val discordChatMemoryRepository: DiscordChatMemoryRepository
): MoodService {
    override fun mood(): Mood {
        val history = discordChatMemoryRepository.findTop10ByOrderByCreatedAtDesc()
        val prompt = """
            Analyze the user's mood based on the recent messages.
            Return ONLY one of these values (single word):
            HAPPY, SAD, ANGRY, TIRED, CALM, ANXIOUS
            
            Messages:
            $history
        """.trimIndent()

        val raw = chatClient.prompt(prompt).call().content()?.trim()?.uppercase()
   return raw.toMoodOrDefault()
    }

    override fun makeHappy(): String {

        val chats = discordChatMemoryRepository
            .findTop10ByOrderByCreatedAtDesc()
            .reversed()

        val history = chats.joinToString("\n") {
            "- ${it.content}"
        }

        val prompt = """
        You are my private assistant helping me support my close friend.

        Based on the recent messages below:

        1) Explain briefly why their mood might be low.
        2) Write ONE short message I can send to them (natural, not cringe).
        3) Give me 3 practical things I can do today to help.

        Rules:
        - No diagnosis
        - No therapy language
        - No dramatic tone
        - Be realistic and human

        Use EXACT format:

        REASON:
        <text>

        MESSAGE:
        <text>

        ACTIONS:
        - <step 1>
        - <step 2>
        - <step 3>

        Recent messages:
        $history
    """.trimIndent()

        return try {
            chatClient.prompt(prompt)
                .call()
                .content()
                ?.take(1800)
                ?: defaultFallback()
        } catch (_: Exception) {
            defaultFallback()
        }
    }

    private fun defaultFallback(): String {
        return """
        REASON:
        They seem emotionally tired and less expressive than usual.

        MESSAGE:
        Hey, you’ve been a bit quiet lately. I just wanted to check in. Do you want to talk about anything?

        ACTIONS:
        - Listen without interrupting or fixing
        - Offer a short call or walk
        - Send something light (meme, song, voice note)
    """.trimIndent()
    }

    private fun String?.toMoodOrDefault(): Mood {
        return try {
            if (this.isNullOrBlank()) Mood.CALM else Mood.valueOf(this)
        } catch (_: Exception) {
            Mood.CALM
        }
    }
}
