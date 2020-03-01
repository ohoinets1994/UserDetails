package com.example.userdetails.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userdetails.extentions.load
import com.example.userdetails.model.Info
import com.example.userdetails.model.User
import com.example.userdetails.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class UserViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val users = MutableLiveData<List<User>>()
    val info = MutableLiveData<Info>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Throwable>()
    private var job: Job? = null

    fun loadUsers() = load(loading, error) {
        val response = NetworkService.api.getMultipleUsers().await()
        info.value = response.info
        users.value = response.results
    }

    fun loadNextPageUsers(nextPage: Int) {
        if (job?.isActive != true) {
            job = load(loading, error) {
                loading.value = false
                val response = NetworkService.api.getNextPageUsers(nextPage.plus(1)).await()
                info.value = response.info
                users.value = users.value?.let { it + response.results } ?: response.results
            }
        }
    }

}