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
    private val _currentPage = MutableLiveData<Int>(0)
    val currentPage: LiveData<Int> = _currentPage

    init {
        getUser()
    }

    fun getUser(page: Int = 0) {
        viewModelScope.launch {
            try {
                val result = if (page == 0) {
                    userRepository.getUsers(false)
                } else {
                    userRepository.loadMoreFromRemote(page)
                }
                if (result is Result.Success) {

                    if (page == 0) {
                        _users.value = result.data!!
                    } else {
                        if (page != _currentPage.value) {
                            val tempList: ArrayList<User> = ArrayList(_users.value!!)
                            tempList.addAll(result.data)
                            _users.value = tempList
                            _currentPage.value = page
                        }
                    }
                } else {
                    if (page == 0) {
                        _users.value = listOf()
                    }
                }

                Log.d(TAG, "getUser: ${_users.value!!.size}")
            } catch (e: Exception) {
                Log.d(TAG, "getUser: $e")
                _users.value = listOf()
            }
        }
    }
}