package com.itc.app.network.apiInterface

import com.tm.models.home.CompatibilityQuestions
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IHomeApi {

    /**
     * Interface Method will return the products call
     */
    @GET("meta/data")
    fun getProducts(): Call<CompatibilityQuestions>

}