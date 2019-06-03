package com.example.actividad2.api

import com.example.actividad2.data.Task
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface RegistrerApi {
    @FormUrlEncoded
    @POST("tareas")
    fun Insertartarea(
        @Field("TaskName") name: String,
        @Field("TaskPlace") place: String,
        @Field("TaskUser") user: String,
        @Field("Description") date: String,
        @Field("DateOfExpiry") description: String
    ): Call<Task>

    @DELETE("tareas")
    fun borrarTareas(
        @Field("TaskName") name: String,
        @Field("TaskPlace") place: String,
        @Field("TaskUser") user: String,
        @Field("Description") date: String,
        @Field("DateOfExpiry") description: String
    ):Call<Task>


}
