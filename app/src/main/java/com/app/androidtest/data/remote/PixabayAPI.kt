package com.app.androidtest.data.remote

import com.app.androidtest.BuildConfig
import com.app.androidtest.data.remote.reponses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery:String,
        @Query("key") apiKey:String = BuildConfig.API_KEY
    ) : Response<ImageResponse>
}