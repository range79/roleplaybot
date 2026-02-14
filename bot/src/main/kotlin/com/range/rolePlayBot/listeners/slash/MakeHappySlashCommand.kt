package com.range.rolePlayBot.listeners.slash

import com.range.rolePlayBot.properties.BotProperties
import com.range.rolePlayBot.service.MoodService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MakeHappySlashCommand(
    private val botProperties: BotProperties,
    private val moodService: MoodService,
    private val appScope: CoroutineScope
) : ListenerAdapter(), SlashCommandDefinition {

    companion object {
        private val LOG = LoggerFactory.getLogger(MakeHappySlashCommand::class.java)
         }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name != "make-happy") return

        if (event.user.name != botProperties.ownerUsername) {
            event.reply("You don't have permission to use this command!")
                .setEphemeral(true)
                .queue()
            return
        }


        event.deferReply(false).queue()

        appScope.launch {
            try {
                val msg = moodService.makeHappy() //
                event.hook.editOriginal(msg).queue()
            } catch (e: Exception) {
                LOG.error("Mood command error", e)
                event.hook.editOriginal("Internal error occurred.").queue()
            }
        }
    }

    override fun data(): CommandData {
        return Commands.slash("make-happy", "Gives owner advice how make your friend mood happier")
    }
}