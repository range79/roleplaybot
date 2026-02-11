package com.range.rolePlayBot.service
interface AiMessageService{
    fun sendMessage(message: String): String?
}