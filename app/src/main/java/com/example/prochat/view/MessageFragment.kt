package com.example.prochat.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.prochat.R
import com.example.prochat.adapter.UserListAdapter
import com.example.prochat.databinding.FragmentMessageBinding
import com.example.prochat.repository.UserListRepository
import com.example.prochat.services.UserListServices
import com.example.prochat.util.SwipeHelperCallback
import com.example.prochat.viewmodel.ProChatViewModelFactory
import com.example.prochat.viewmodel.UserListViewModel

class MessageFragment : Fragment() {

    private var mItemTouchHelper: ItemTouchHelper? = null
    private val userListAdapter = UserListAdapter()
    private lateinit var viewModel: UserListViewModel
    private lateinit var binding: FragmentMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageBinding.inflate(inflater, container, false)
        val view = binding.root
        val userListServices = UserListServices()
        val userListRep = UserListRepository(userListServices)
        viewModel = ViewModelProvider(
            this,
            ProChatViewModelFactory(userListRep)
        )[UserListViewModel::class.java]
        viewModel.getUserList(view.context)
        binding.recyclerView.adapter=userListAdapter
        viewModel.userList.observe(viewLifecycleOwner){
            userListAdapter.setList(it)
        }
        val callback: ItemTouchHelper.Callback = SwipeHelperCallback(userListAdapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper?.attachToRecyclerView(binding.recyclerView)
        return view
    }
}