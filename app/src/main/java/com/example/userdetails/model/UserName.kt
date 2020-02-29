package com.example.userdetails.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserName(val title: String, val first: String, val last: String) : Parcelable