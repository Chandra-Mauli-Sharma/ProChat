package com.example.prochat.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.prochat.model.ChatMessage
import com.example.prochat.services.ChatServices

class ChatRepository(private val chatServices: ChatServices) {
    suspend fun sendMessage(context: Context)=chatServices.sendMessage(context)
    fun getMessageList(context: Context,uid:Int): ArrayList<ChatMessage> =chatServices.getMessageList(context,uid)
    fun getUser(context: Context,uid:Int): String =chatServices.getUser(context,uid)
}