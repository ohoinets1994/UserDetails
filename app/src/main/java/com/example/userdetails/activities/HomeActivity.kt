package com.example.userdetails.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userdetails.R
import com.example.userdetails.fragments.UsersListFragments

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainer, UsersListFragments())
            show(UsersListFragments())
            commit()
        }
    }
}
