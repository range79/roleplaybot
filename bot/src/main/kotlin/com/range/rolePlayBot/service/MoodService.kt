package com.range.rolePlayBot.service

import com.range.rolePlayBot.enums.Mood

interface MoodService {
    fun mood(): Mood
    fun makeHappy(): String
}