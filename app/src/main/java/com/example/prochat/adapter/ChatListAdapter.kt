package com.example.prochat.adapter

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.prochat.R
import com.example.prochat.model.ChatMessage
import com.example.prochat.util.ItemTouchHelperAdapter
import com.example.prochat.view.ChatFragment

class ChatListAdapter(val chatFragment: ChatFragment): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    ItemTouchHelperAdapter {
    var chatList= mutableListOf<ChatMessage>()

    fun setList(chatList: List<ChatMessage>){
        this.chatList=chatList.toMutableList()
        notifyDataSetChanged()
    }
    companion object {
        const val SENDER_VIEW_TYPE = 1
        const val RECEIVER_VIEW_TYPE = 2
    }

    class SenderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var senderMsg: TextView = itemView.findViewById(R.id.textView7)
        var receiverDetail:ConstraintLayout=itemView.findViewById(R.id.constraintLayout3)
        var receiverName:TextView=itemView.findViewById(R.id.textView9)
    }

    class ReceiverViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var receiverMsg: TextView = itemView.findViewById(R.id.textView8)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder=if (viewType == SENDER_VIEW_TYPE) {
        SenderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.sender_element, parent, false)
        )
    } else {
        ReceiverViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.receiver_element, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.javaClass.kotlin==SenderViewHolder::class) {
            (holder as SenderViewHolder).senderMsg.text = chatList[position].message
            if(position!=0){
                if (chatList[position - 1].id != 0) {
                    holder.receiverDetail.visibility = View.VISIBLE
                    holder.receiverName.text=chatFragment.view?.findViewById<TextView>(R.id.textView)?.text
                } else {
                    holder.receiverDetail.visibility = View.GONE
                }
            }
        } else {
            (holder as ReceiverViewHolder).receiverMsg.text = chatList[position].message
        }
    }

    override fun getItemCount(): Int =chatList.size

    override fun getItemViewType(position: Int): Int =
        if (chatList[position].id==0) {
            SENDER_VIEW_TYPE
        } else {
            RECEIVER_VIEW_TYPE
        }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        return false
    }

    override fun onItemDismiss(position: Int) {
        chatList.removeAt(position)
        notifyItemRemoved(position)

//        onSwiped()
    }

}