package com.range.pierrotdiscorcdbot.service
interface AiMessageService{
    fun sendMessage(message: String): String?
}