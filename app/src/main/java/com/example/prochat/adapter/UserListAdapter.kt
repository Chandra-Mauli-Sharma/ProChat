package com.example.prochat.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.prochat.R
import com.example.prochat.model.User
import com.example.prochat.util.ItemTouchHelperAdapter
import com.example.prochat.view.MessageFragment
import com.example.prochat.view.MessageFragmentDirections

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>(),ItemTouchHelperAdapter {

    var userList = mutableListOf<User>()

    fun setList(userList: List<User>) {
        this.userList = userList.toMutableList()
        notifyDataSetChanged()
    }

    class UserListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageUrl: ImageFilterView = view.findViewById(R.id.imageFilterView)
        val name: TextView = view.findViewById(R.id.textView2)
        val lastMessage: TextView = view.findViewById(R.id.textView3)
        val lastSeen: TextView = view.findViewById(R.id.textView4)
        val messageCount: TextView = view.findViewById(R.id.textView5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.user_element, parent, false)
        return UserListViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val item = userList[position]
        holder.name.text = item.name
        holder.lastSeen.text = item.lastSeen
        holder.lastMessage.text = item.lastMessage
        holder.messageCount.text = item.messageCount.toString()

        holder.itemView.setOnClickListener {
            val action= MessageFragmentDirections.actionMessageFragmentToChatFragment(item.uid)
            holder.itemView.findFragment<MessageFragment>().view?.findNavController()
                ?.navigate(action)
        }
    }

    override fun getItemCount(): Int = userList.size
    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        return false
    }

    override fun onItemDismiss(position: Int) {
        userList.removeAt(position)
        notifyItemRemoved(position)
    }
}