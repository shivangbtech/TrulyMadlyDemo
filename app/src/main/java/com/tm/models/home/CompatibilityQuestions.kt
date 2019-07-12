package com.tm.models.home

import android.os.Parcelable
import com.tm.models.BaseResponseModal
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompatibilityQuestions(val compatibility_questions: List<CompatibilityQuestion>):BaseResponseModal(),
    Parcelable