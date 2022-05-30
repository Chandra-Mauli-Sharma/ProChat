package com.example.prochat.repository

import android.content.Context
import com.example.prochat.model.User
import com.example.prochat.services.UserListServices

class UserListRepository(private val userListServices: UserListServices) {
    fun getUserList(context: Context)=userListServices.getUserList(context)
}