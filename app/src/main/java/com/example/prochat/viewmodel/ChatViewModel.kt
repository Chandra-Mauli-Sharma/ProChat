package com.example.prochat.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prochat.model.ChatMessage
import com.example.prochat.repository.ChatRepository
import kotlinx.coroutines.*

class ChatViewModel(private val repo:ChatRepository):ViewModel() {
    var job: Job? = null
    var chatList: MutableLiveData<List<ChatMessage>> = MutableLiveData<List<ChatMessage>>()
    var uname:MutableLiveData<String> = MutableLiveData<String>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Exception handled: ${throwable.localizedMessage}")
    }

    fun getMessageList(context:Context,uid:Int){
        job= CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            withContext(Dispatchers.Main) {
                chatList.postValue(repo.getMessageList(context,uid))
            }
        }
    }
    fun getUser(context:Context,uid:Int){
        job= CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            withContext(Dispatchers.Main) {
                uname.postValue(repo.getUser(context,uid))
            }
        }
    }

    fun sendMessage(context: Context){
        job= CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            withContext(Dispatchers.Main) {
                repo.sendMessage(context)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}