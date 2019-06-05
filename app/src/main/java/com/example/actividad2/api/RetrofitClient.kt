package com.example.actividad2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.microedition.khronos.egl.EGL

object RetrofitClient {
    private const val locahost="127.0.0.1"
    private const val url="http://$locahost/webservice/"

    var retrofit= Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()!!

    var service = retrofit.create(RegistrerApi::class.java)!!



}
