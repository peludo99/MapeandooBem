package com.peludo.mapeando0bem

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndPoint {


    @GET("data.php")
    fun data() : Call<Notemodel>

    @FormUrlEncoded
    @POST("criarUsuario.php")
    fun create(
        @Field("email") email: String,
        @Field("senha") senha: String,
        @Field("nick") nick: String
    ) : Call<SubmitModel>


    @FormUrlEncoded
    @POST("LogarUsuario.php")
    fun logar(
        @Field("email") email: String,
        @Field("senha") senha: String,
    ) : Call<SubmitModel>



}