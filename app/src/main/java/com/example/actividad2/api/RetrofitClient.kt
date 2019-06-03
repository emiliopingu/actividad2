package com.example.actividad2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.microedition.khronos.egl.EGL

object RetrofitClient {

    private const val url="http://localhost/phpmyadmin/sql.php?server=1&db=tareas&table=task&pos=0"

    var retrofit= Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(RegistrerApi::class.java)


}
