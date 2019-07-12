package com.tm.network.apiInterface

import com.tm.models.home.CompatibilityQuestions
import retrofit2.Call
import retrofit2.http.GET

interface IHomeApi {

    /**
     * Interface Method will return the products call
     */
    @GET("meta/data")
    fun getProducts(): Call<CompatibilityQuestions>

}