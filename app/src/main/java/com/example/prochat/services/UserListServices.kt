package com.example.prochat.services

import android.content.Context
import com.example.prochat.model.User
import com.example.prochat.util.JsonFileFetch
import org.json.JSONException
import org.json.JSONObject

class UserListServices {
    fun getUserList(context: Context):List<User>{
        val jsonResponse = ArrayList<User>()

        try{
            val obj = JSONObject(JsonFileFetch().getJSONFromAssets(context,"UserList.json")!!)

            val userListArray = obj.getJSONArray("Users")

            for(i in 0 until userListArray.length()){
                val userListResponse = userListArray.getJSONObject(i)
                val uid=userListResponse.getInt("id")
                val userListName = userListResponse.getString("name")
                val imageUrl= userListResponse.getString("imageUrl")
                val lastMessage = userListResponse.getString("lastMessage")
                val lastSeen = userListResponse.getString("lastSeen")
                val messageCount=userListResponse.getInt("Message Count")

                jsonResponse.add(User(imageUrl, userListName, lastMessage, lastSeen, messageCount,uid))
            }
        } catch(e: JSONException){
            e.printStackTrace()
        }
        return jsonResponse
    }
}