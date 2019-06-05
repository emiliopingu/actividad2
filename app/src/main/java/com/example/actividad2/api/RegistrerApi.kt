package com.example.actividad2.api

import com.example.actividad2.data.All
import com.example.actividad2.data.Task
import retrofit2.Call
import retrofit2.http.*


interface RegistrerApi {

    @GET("tareas.php") fun getPeople(): Call<List<Task>>


    @FormUrlEncoded
    @POST("insertarDatos.php")
    fun Insertartarea(
        @Field("TaskName") nombre: String,
        @Field("TaskPlace") lugar: String,
        @Field("TaskUser") usuario: String,
        @Field("Description") fecha: String,
        @Field("DateOfExpiry") descripcion: String
    ): Call<Task>

    @DELETE
    fun borrarTareas(
        @Field("TaskName") name: String,
        @Field("TaskPlace") place: String,
        @Field("TaskUser") user: String,
        @Field("Description") date: String,
        @Field("DateOfExpiry") description: String
    ):Call<Task>



}
