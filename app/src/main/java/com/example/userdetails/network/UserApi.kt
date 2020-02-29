package com.example.userdetails.network

import com.example.userdetails.model.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("api/")
    fun getUser(): Deferred<UserResponse>

    @GET("api/")
    fun getMultipleUsers(@Query("results") items: Int = 20): Deferred<UserResponse>
}