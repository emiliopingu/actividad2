package com.example.actividad2.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Task(

    @SerializedName("TaskName")
    @Expose
    var name: String? = null,


    @SerializedName("TaskPlace")
    @Expose
    var place: String? = null,


    @SerializedName("TaskUser")
    @Expose
    var user: String? = null,


    @SerializedName("DateOfExpiry")
    @Expose
    var date: String? = null,

    @SerializedName("Description")
    @Expose
    var description: String? = null
)





