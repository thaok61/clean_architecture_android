package com.me.architecture_study.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.me.architecture_study.data.source.Result
import com.me.architecture_study.data.source.UsersRepository
import com.me.architecture_study.model.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

private val TAG = UserViewModel::class.java.simpleName

class UserViewModel @Inject constructor(private val userRepository: UsersRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(ListUserScreenState())
    val uiState: StateFlow<ListUserScreenState> = _uiState

    init {
        getUser()
    }

    private fun getUser(page: Int = 0) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            userRepository.getUsers().collectLatest { paging ->
                _uiState.update { it.copy(userItem = paging) }
            }
        }
        _uiState.update { it.copy(isLoading = false) }
    }
}

data class ListUserScreenState(
    val isLoading: Boolean = false,
    val userItem: PagingData<User> = PagingData.empty(),
)