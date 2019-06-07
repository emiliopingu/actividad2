package com.example.actividad2.api

import com.example.actividad2.data.All
import com.example.actividad2.data.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import okhttp3.ResponseBody
import retrofit2.http.DELETE








interface RegistrerApi {

    @GET("tareas.php") fun getTask(): Call<List<Task>>

    @FormUrlEncoded
    @POST("insertarDatos.php")
    fun insertTask(
        @Field("nombre") nombre: String,
        @Field("lugar") lugar: String,
        @Field("usuario") usuario: String,
        @Field("fecha") fecha: String,
        @Field("descripcion") descripcion: String
    ):Call<ResponseBody>

    @FormUrlEncoded
    @POST("actualizarDatos.php")
    fun updateTask(
        @Field("nombre") nombre: String,
        @Field("lugar") lugar: String,
        @Field("usuario") usuario: String,
        @Field("fecha") fecha: String,
        @Field("descripcion") descripcion: String
    ):Call<ResponseBody>



    @FormUrlEncoded
    @POST("borrarDatos.php")
    fun deleteTask(@Field("nombre")nombre: String):Call<ResponseBody>




}
