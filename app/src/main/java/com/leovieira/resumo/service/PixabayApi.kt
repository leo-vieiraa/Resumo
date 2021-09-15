package com.leovieira.resumo.service

import com.leovieira.resumo.BuildConfig
import com.leovieira.resumo.model.PixabayImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun fetchImage(@Query("key") key: String = BuildConfig.API_KEY, @Query("q") q: String, @Query("lang") lang: String = "pt") : Response<PixabayImage>

}