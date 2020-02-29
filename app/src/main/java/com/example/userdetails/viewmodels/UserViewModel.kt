package com.example.userdetails.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userdetails.model.User
import com.example.userdetails.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val users = MutableLiveData<List<User>>()

    fun loadUsers() = launch {
        try {
            val userList = NetworkService.api.getMultipleUsers().await().results
            users.postValue(userList)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}