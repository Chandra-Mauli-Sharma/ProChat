package com.example.prochat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.prochat.repository.ChatRepository
import com.example.prochat.repository.UserListRepository

class ProChatViewModelFactory<RP>(private val repo:RP):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(UserListViewModel::class.java)){
            UserListViewModel(this.repo as UserListRepository) as T
        } else if(modelClass.isAssignableFrom(ChatViewModel::class.java)){
            ChatViewModel(this.repo as ChatRepository) as T
        } else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}