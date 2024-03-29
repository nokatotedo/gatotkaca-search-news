package com.schoters.gatotkaca.api

import com.schoters.gatotkaca.data.EverythingResponse
import com.schoters.gatotkaca.data.TopResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Api {
    @GET("everything")
    fun getEverything(
        @QueryMap parameters: HashMap<String, String>
    ): Call<EverythingResponse>

    @GET("top-headlines")
    fun getTop(
        @QueryMap parameters: HashMap<String, String>
    ): Call<TopResponse>
}