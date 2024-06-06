package com.gcendon.terremotoapi_gc

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIServices {
    @GET
    suspend fun getQuakes30(@Url url:String): Response<Earthquakes>

    @GET
    suspend fun getQuakesDay(@Url url:String): Response<Earthquakes>
}