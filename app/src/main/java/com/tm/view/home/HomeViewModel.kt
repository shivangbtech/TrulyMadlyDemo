package com.itc.app.views.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itc.app.components.Event
import com.tm.models.BaseResponseModal
import com.tm.models.home.CompatibilityQuestions
import com.tm.network.ApiCallback
import com.tm.network.DataServiceFactory
import com.tm.network.ResponseHandler

class HomeViewModel : ViewModel() {

    internal var mldProducts = MutableLiveData<CompatibilityQuestions>()
    internal var mldError = MutableLiveData<Event<BaseResponseModal>>()

    internal fun getListRows(): MutableLiveData<CompatibilityQuestions> {
        val apiInterface = DataServiceFactory.getInstance().homeApi
        val call = apiInterface.getProducts()
        call.enqueue(ResponseHandler<CompatibilityQuestions>(object : ApiCallback<CompatibilityQuestions> {
            override fun onSuccess(t: CompatibilityQuestions?) {
                mldProducts.value = t as CompatibilityQuestions
            }

            override fun onError(responseModal: BaseResponseModal?) {
                mldError.value = Event(responseModal!!)
            }
        }))

        return mldProducts
    }

}