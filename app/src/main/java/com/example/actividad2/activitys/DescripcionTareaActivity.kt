package com.example.actividad2.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.actividad2.R
import kotlinx.android.synthetic.main.activity_descripcion_tarea.*

class DescripcionTareaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion_tarea)
        val description = intent.getStringExtra("tareaDescripcion")

        tvDescripcionTarea.text = description


    }


}
