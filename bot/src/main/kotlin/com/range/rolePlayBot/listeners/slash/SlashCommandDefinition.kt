package com.range.rolePlayBot.listeners.slash

import net.dv8tion.jda.api.interactions.commands.build.CommandData

interface SlashCommandDefinition {
    fun data(): CommandData
}