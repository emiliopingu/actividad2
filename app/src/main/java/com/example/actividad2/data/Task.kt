package com.example.actividad2.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Task(

    @SerializedName("nombre")
    @Expose
    var name: String? = null,


    @SerializedName("lugar")
    @Expose
    var place: String? = null,


    @SerializedName("usuario")
    @Expose
    var user: String? = null,


    @SerializedName("fecha")
    @Expose
    var date: String? = null,

    @SerializedName("descripcion")
    @Expose
    var description: String? = null,

    @SerializedName("fechaDeCaducidad")
    @Expose
    var fechaDeCaducidad: String? = null

)





