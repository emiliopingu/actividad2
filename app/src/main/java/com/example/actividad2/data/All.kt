package com.example.actividad2.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class All (
    @SerializedName("Recopilation_Task")
    @Expose
    var tareas: List<Task>? = null
)
