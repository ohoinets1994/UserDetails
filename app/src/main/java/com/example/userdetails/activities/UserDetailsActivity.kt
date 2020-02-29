package com.example.userdetails.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userdetails.R
import com.example.userdetails.model.User
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AppCompatActivity() {
    companion object {
        private const val KEY_USER = "key.user"

        fun newIntent(context: Context, user: User): Intent =
            Intent(context, UserDetailsActivity::class.java).apply { putExtra(KEY_USER, user) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        setSupportActionBar(tbUserDetails)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}