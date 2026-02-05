package com.range.pierrotdiscorcdbot.configuration

import com.range.pierrotdiscorcdbot.properties.BotProperties
import org.springframework.context.annotation.Configuration

@Configuration
class JDAConfiguration(
    private val botProperties: BotProperties
) {


}