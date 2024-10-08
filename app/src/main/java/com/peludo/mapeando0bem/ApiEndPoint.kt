package com.peludo.mapeando0bem

import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {


    @GET("data.php")
    fun data() : Call<Notemodel>
}