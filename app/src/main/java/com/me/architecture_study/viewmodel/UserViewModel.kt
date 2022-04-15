package com.me.architecture_study.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.architecture_study.data.source.Result
import com.me.architecture_study.data.source.UserDataSource
import com.me.architecture_study.data.source.UsersRepository
import com.me.architecture_study.model.User
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

private val TAG = UserViewModel::class.java.simpleName

class UserViewModel @Inject constructor(private val userRepository: UsersRepository) :
    ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            try {
                val result = userRepository.getUsers(false)
                if (result is Result.Success) {
                    _users.value = result.data!!
                } else {
                    _users.value = listOf()
                }

                Log.d(TAG, "getUser: ${_users.value!!.size}")
            } catch (e: Exception) {
                Log.d(TAG, "getUser: $e")
                _users.value = listOf()
            }
        }
    }
}