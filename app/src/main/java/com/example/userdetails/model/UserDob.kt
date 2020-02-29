package com.example.userdetails.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDob(val date: String, val age: Int) : Parcelable