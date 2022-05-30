package com.example.prochat.model

data class User(
    val imageUrl: String,
    val name: String,
    val lastMessage: String,
    val lastSeen: String,
    val messageCount: Int,
    val uid: Int
)