package com.range.rolePlayBot.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "bot")
data class BotProperties (
    var clientUsername:String?,
    var token:String?,
    var ownerUsername:String?,
)