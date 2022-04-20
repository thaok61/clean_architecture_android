package com.me.architecture_study.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.me.architecture_study.databinding.FragmentListUserBinding
import com.me.architecture_study.viewmodel.UserViewModel
import dagger.android.support.DaggerFragment
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
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = UserAdapter()
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if (lastVisibleItem + THRESHOLD >= totalItemCount) {
                    Log.d(TAG, "onScrolled: ${totalItemCount / 20}")
                    viewModel.getUser(totalItemCount / 20)

                }
                Log.d(TAG, "onScrolled: totalItemCount: $totalItemCount lastVisibleItem: $lastVisibleItem")
            }
        })
        // Inflate the layout for this fragment
        return binding.root
    }
}