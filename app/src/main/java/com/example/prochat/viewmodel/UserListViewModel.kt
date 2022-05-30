package com.example.prochat.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prochat.model.User
import com.example.prochat.repository.UserListRepository
import kotlinx.coroutines.*

class UserListViewModel(private val repo: UserListRepository) : ViewModel() {

    var job: Job? = null
    var userList:MutableLiveData<List<User>> =MutableLiveData<List<User>>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Exception handled: ${throwable.localizedMessage}")
    }
    fun getUserList(context:Context){
        job= CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            withContext(Dispatchers.Main) {
                userList.postValue(repo.getUserList(context))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}