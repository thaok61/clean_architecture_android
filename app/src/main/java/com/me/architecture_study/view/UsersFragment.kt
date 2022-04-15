package com.me.architecture_study.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.me.architecture_study.R
import com.me.architecture_study.databinding.FragmentListUserBinding
import com.me.architecture_study.viewmodel.UserViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UsersFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<UserViewModel> { viewModelFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListUserBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = UserAdapter()
        // Inflate the layout for this fragment
        return binding.root
    }
}