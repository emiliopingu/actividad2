package com.example.actividad2.activitys

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.Button
import android.widget.Toast
import com.example.actividad2.R
import com.example.actividad2.item.Tareas
import kotlinx.android.synthetic.main.activity_formulario_tarea.*

class FormularioTareaActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_tarea)




        val button = findViewById<Button>(R.id.bFormularioEnviar)
        button.setOnClickListener {

                val intent = Intent(this@FormularioTareaActivity, MainActivity::class.java)
                intent.putExtra("NombreProblema",etNombreProblema.text.toString())
                intent.putExtra("LugarTarea", etLugarTarea.text.toString())
                intent.putExtra("PersonaTarea", etPersonaTarea.text.toString())
                intent.putExtra("DescripcionTarea", etDescripcionTarea.text.toString())
                intent.putExtra("FechaTarea", etFechaTarea.text.toString())
                this.startActivity(intent)

        }
    }



}
