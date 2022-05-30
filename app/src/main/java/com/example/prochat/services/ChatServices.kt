package com.example.prochat.services

import android.content.Context
import com.example.prochat.model.ChatMessage
import com.example.prochat.util.JsonFileFetch
import org.json.JSONException
import org.json.JSONObject

class ChatServices {
    suspend fun sendMessage(context: Context) {
        val jsonResponse = ArrayList<ChatMessage>()
        try {
            val obj = JSONObject(JsonFileFetch().getJSONFromAssets(context, "ChatList.json")!!)

            val chatListArray = obj.getJSONArray("Chats")

            chatListArray.put(
                (mutableMapOf(
                    "id" to 0,
                    "message" to "hey",
                ) as Map<*, *>?)?.let {
                    JSONObject(
                        it
                    )
                }
            )
            JsonFileFetch().saveJSONToAssets(context, "ChatList.json", chatListArray.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }




    fun getMessageList(context: Context,uid:Int): ArrayList<ChatMessage> {
        val jsonResponse=ArrayList<ChatMessage>()
        try{
            val obj=JSONObject(JsonFileFetch().getJSONFromAssets(context,"ChatList.json") !!)
            val chatListArray=obj.getJSONArray("0${uid}")

            for(i in 0 until chatListArray.length()){
                val chatListResponse = chatListArray.getJSONObject(i)
                val chatId = chatListResponse.getInt("id")
                val message= chatListResponse.getString("message")
                jsonResponse.add(ChatMessage(chatId,message))
            }
        }catch(e: JSONException){
            e.printStackTrace()
        }
        return jsonResponse
    }

    fun getUser(context:Context,uid:Int):String{
        try{
            val obj=JSONObject(JsonFileFetch().getJSONFromAssets(context,"UserList.json") !!)
            val userListArray=obj.getJSONArray("Users")
            for(i in 0 until userListArray.length()){
                val userListResponse = userListArray.getJSONObject(i)
                if(userListResponse.getInt("id")==uid){
                    return userListResponse.getString("name")
                }
            }
        } catch(e: JSONException){
            e.printStackTrace()
        }
        return "user"
    }

}