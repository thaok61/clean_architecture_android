package com.me.architecture_study.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.me.architecture_study.databinding.FragmentListUserBinding
import com.me.architecture_study.viewmodel.UserViewModel
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

private val TAG = UsersFragment::class.java.simpleName
private const val THRESHOLD = 2

class UsersFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<UserViewModel> { viewModelFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListUserBinding.inflate(inflater)
        val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        val adapter = UserAdapter()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch {
            viewModel.uiState.map { it.userItem }.collectLatest(adapter::submitData)
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}