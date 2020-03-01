package com.example.userdetails.extentions

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.userdetails.R
import retrofit2.HttpException
import java.net.UnknownHostException

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, Observer { observer(it) })
}

fun Throwable.getErrorMessage(context: Context): String =
    context.getString(
        when (this) {
            is UnknownHostException -> R.string.error_no_internet_connection
            is HttpException -> when (code()) {
                in 400..499 -> R.string.error_status_code_400
                in 500..599 -> R.string.error_status_code_500
                else -> R.string.error_something_went_wrong
            }
            else -> R.string.error_something_went_wrong
        }
    )

fun fullName(first: String, last: String): String = "$first $last"