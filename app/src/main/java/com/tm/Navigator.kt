package com.tm.views

import android.content.Intent
import com.tm.base.BaseActivity
import com.tm.constants.AppConstants
import com.tm.models.home.CompatibilityQuestion
import com.tm.view.carousel.CarouselActivity


class Navigator private constructor() {
    init {
        println("This ($this) is a singleton")
    }

    private object Holder {
        val INSTANCE = Navigator()
    }

    companion object {
        val instance: Navigator by lazy { Holder.INSTANCE }
    }

    fun navigateToCarousel(activity: BaseActivity, compatibilityQuestions: ArrayList<CompatibilityQuestion>, position: Int) {
        val intent: Intent = Intent(activity, CarouselActivity::class.java)
        intent.putParcelableArrayListExtra(AppConstants.PARAM_PRODUCT, compatibilityQuestions)
        intent.putExtra(AppConstants.PARAM_POSITION, position)
        activity.startActivity(intent)
    }
}