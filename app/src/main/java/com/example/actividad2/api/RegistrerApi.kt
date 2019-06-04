package com.example.actividad2.api

import com.example.actividad2.data.Task
import retrofit2.Call
import retrofit2.http.*


interface RegistrerApi {
    @FormUrlEncoded
    @POST("insertarDatos.php")
    fun Insertartarea(
        @Field("TaskName") name: String,
        @Field("TaskPlace") place: String,
        @Field("TaskUser") user: String,
        @Field("Description") date: String,
        @Field("DateOfExpiry") description: String
    ): Call<Task>

    @DELETE
    fun borrarTareas(
        @Field("TaskName") name: String,
        @Field("TaskPlace") place: String,
        @Field("TaskUser") user: String,
        @Field("Description") date: String,
        @Field("DateOfExpiry") description: String
    ):Call<Task>

    @GET("insertarDatos.php")
    fun recogerTarea(): Call<Task>


}
