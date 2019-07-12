package com.tm.models.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Style(val large: String, val medium: String, val original: String, val thumb:String): Parcelable