package com.range.pierrotdiscorcdbot.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID
import javax.xml.crypto.dsig.keyinfo.PGPData

@Entity
@Table(name = "chat_memory")
data class ChatMemoryEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(columnDefinition = "TEXT")
    val content: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val isBot: Boolean = false,
)