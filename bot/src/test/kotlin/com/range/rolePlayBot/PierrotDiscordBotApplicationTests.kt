package com.range.rolePlayBot

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest
class PierrotDiscordBotApplicationTests {
    @MockitoBean
    lateinit var jda: net.dv8tion.jda.api.JDA
    @Test
    fun contextLoads() {
    }

}
