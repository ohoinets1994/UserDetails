package com.example.userdetails.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.userdetails.R
import com.example.userdetails.extentions.getErrorMessage
import kotlinx.android.synthetic.main.layout_error_message.view.*

class ErrorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private var shouldShowError = false

    var error: String?
        set(value) {
            tvErrorMessage.text = value
            shouldShowError = true
            showError()
        }
        get() = tvErrorMessage.text.toString()

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_error_message, this, true)
        val ta = context.theme.obtainStyledAttributes(attrs, R.styleable.ErrorView, 0, 0)
        error = ta.getString(R.styleable.ErrorView_errorString)
        hideError()
        ta.recycle()
    }

    fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    fun setError(exception: Throwable) {
        error = exception.getErrorMessage(context)
    }

    fun showLoading() {
        isVisible = true
        pbErrorLoading.isVisible = true
        tvErrorMessage.isVisible = shouldShowError
    }

    fun hideLoading() {
        isVisible = false
    }

    fun showError() {
        shouldShowError = true
        isVisible = true
        pbErrorLoading.isVisible = false
        tvErrorMessage.isVisible = true
    }

    fun hideError() {
        shouldShowError = false
        tvErrorMessage.isVisible = false
    }
}