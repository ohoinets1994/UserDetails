package com.example.userdetails.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val gender: String,
    val name: UserName,
    val email: String,
    val dob: UserDob,
    val phone: String,
    val picture: UserPictures
) : Parcelable