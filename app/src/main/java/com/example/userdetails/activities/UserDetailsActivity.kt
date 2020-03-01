package com.example.userdetails.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.userdetails.R
import com.example.userdetails.extentions.fullName
import com.example.userdetails.extentions.load
import com.example.userdetails.model.User
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_CALL = 1
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

            imgCall.setOnClickListener { makePhoneCall(phone) }
            imgMessage.setOnClickListener { makeChat(phone) }
        }
    }

    private fun makePhoneCall(phoneNumber: String) {
        if (phoneNumber.trim().isNotEmpty()) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    REQUEST_CALL)
            } else {
                val callIntent: Intent = Uri.parse("tel:$phoneNumber").let { number ->
                    Intent(Intent.ACTION_DIAL, number)
                }
                startActivity(callIntent)
            }
        } else {
            Toast.makeText(this, "Phone number is empty", Toast.LENGTH_LONG).show()
        }
    }

    private fun makeChat(phoneNumber: String) {
        if (phoneNumber.trim().isNotEmpty()) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.SEND_SMS),
                    REQUEST_CALL)
            } else {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    data = Uri.parse("smsto:$phoneNumber")
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
        } else {
            Toast.makeText(this, "Phone number is empty", Toast.LENGTH_LONG).show()
        }
    }
}