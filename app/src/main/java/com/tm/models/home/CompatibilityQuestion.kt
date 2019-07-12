package com.tm.models.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompatibilityQuestion(val id: String, val cross: String, val question:String, val tick: String,
                                  val style:Style): Parcelable