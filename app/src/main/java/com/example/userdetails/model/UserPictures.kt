package com.example.userdetails.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserPictures(val large: String, val medium: String, val thumbnail: String) : Parcelable