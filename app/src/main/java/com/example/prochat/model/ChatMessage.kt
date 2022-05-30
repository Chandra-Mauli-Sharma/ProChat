package com.example.prochat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ChatMessage(
    val id: Int,
    val message: String
)