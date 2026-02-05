package com.range.pierrotdiscorcdbot.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "bot")
data class BotProperties (
    var ownerUsername:String?,
    var token:String?,
)