package com.example.prochat.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.prochat.R
import com.example.prochat.adapter.ChatListAdapter
import com.example.prochat.adapter.UserListAdapter
import com.example.prochat.databinding.FragmentChatBinding
import com.example.prochat.databinding.FragmentMessageBinding
import com.example.prochat.repository.ChatRepository
import com.example.prochat.services.ChatServices
import com.example.prochat.util.SwipeHelperCallback
import com.example.prochat.viewmodel.ChatViewModel
import com.example.prochat.viewmodel.ProChatViewModelFactory
import com.example.prochat.viewmodel.UserListViewModel

class ChatFragment : Fragment() {

    private var mItemTouchHelper: ItemTouchHelper? = null
    private val chatListAdapter = ChatListAdapter(this)
    private lateinit var viewModel: ChatViewModel
    private lateinit var binding: FragmentChatBinding
    val args:ChatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentChatBinding.inflate(inflater,container,false)
        val view = binding.root
        val chatListServices=ChatServices()
        val chatRepository = ChatRepository(chatListServices)
        viewModel = ViewModelProvider(
            this,
            ProChatViewModelFactory(chatRepository)
        )[ChatViewModel::class.java]
        viewModel.getUser(view.context,args.uid)
        viewModel.uname.observe(viewLifecycleOwner){
            binding.textView.text=it
        }

        binding.chatList.adapter=chatListAdapter
        viewModel.getMessageList(view.context,args.uid)

        val callback: ItemTouchHelper.Callback = SwipeHelperCallback(chatListAdapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper?.attachToRecyclerView(binding.chatList)
        binding.textInputLayout.setEndIconOnClickListener {
            viewModel.sendMessage(view.context)
            viewModel.getMessageList(view.context,args.uid)
        }
        binding.imageView.setOnClickListener {
            view.findNavController().popBackStack()
        }
        viewModel.chatList.observe(viewLifecycleOwner){
            chatListAdapter.setList(it)
        }
        return view
    }
}