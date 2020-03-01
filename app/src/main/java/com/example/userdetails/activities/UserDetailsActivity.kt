package com.example.userdetails.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userdetails.R
import com.example.userdetails.extentions.fullName
import com.example.userdetails.extentions.load
import com.example.userdetails.model.User
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AppCompatActivity() {
    companion object {
        private const val KEY_USER = "key.user"

        fun newIntent(context: Context, user: User): Intent =
            Intent(context, UserDetailsActivity::class.java).apply { putExtra(KEY_USER, user) }
    }

    private val user: User by lazy { intent.getParcelableExtra<User>(KEY_USER) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        setSupportActionBar(tbUserDetails)

        user.apply {
            imgUserAvatar.load(picture.large)
            tvUserName.text = fullName(name.first, name.last)
            tvUserGender.text = getString(R.string.gender, gender)

            val dob = dob.date.split("T").first()
            tvUserDateOfBirth.text = getString(R.string.dob, dob)
            tvUserPhoneNumber.text = getString(R.string.phone_number, phone)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}