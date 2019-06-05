package com.example.actividad2.api

import com.example.actividad2.data.All
import com.example.actividad2.data.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded




interface RegistrerApi {

    @GET("tareas.php") fun getTask(): Call<List<Task>>

    @FormUrlEncoded
    @POST("/insert.php")
    fun insertTask(
        @Field("TaskName") name: String,
        @Field("TaskPlace") place: String,
        @Field("TaskUser") user: String,
        @Field("Description") date: String,
        @Field("DateOfExpiry") description: String
    ):Call<Task>


    @DELETE
    fun borrarTareas(
        @Field("TaskName") name: String,
        @Field("TaskPlace") place: String,
        @Field("TaskUser") user: String,
        @Field("Description") date: String,
        @Field("DateOfExpiry") description: String
    ):Call<Task>



}
