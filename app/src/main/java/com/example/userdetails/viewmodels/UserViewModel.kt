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
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Throwable>()

    fun loadUsers() = launch {
        loading.value = true
        try {
            val userList = NetworkService.api.getMultipleUsers().await().results
            users.postValue(userList)
            loading.postValue(false)
        } catch (e: Exception) {
            loading.postValue(false)
            error.postValue(e)
        }
    }
}