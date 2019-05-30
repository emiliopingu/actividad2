package com.example.actividad2.presentation

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.actividad2.R
import kotlinx.android.synthetic.main.activity_descripcion_tarea.*
import kotlinx.android.synthetic.main.tareas_list.*

class DescripcionTareaActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion_tarea)
        val description = intent.getStringExtra("tareaDescripcion")
        val usuario = intent.getStringExtra("usuarioDescripcion")
        val problema = intent.getStringExtra("problemaDescripcion")
        val lugar = intent.getStringExtra("lugarDescripcion")
        val fecha = intent.getStringExtra("fechaDescripcion")

        tvNombreDescripcion.text = "TITULO DEL PROBLEMA  \n $problema"
        tvUsuario.text="USUARIO REMITENTE  \n $usuario"
        tvLugarDescripcion.text="LUGAR DEL PROBLEMA \n $lugar"
        tvFechaDescripcion.text = "FECHA CADUCIDAD \n $fecha"
        tvDescripcionTarea.text = "DESCCRIPCION \n $description"

    }


}
