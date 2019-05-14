package com.example.actividad2.activitys

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import com.example.actividad2.R
import com.example.actividad2.adapter.AdapterTareas
import com.example.actividad2.item.Tareas
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val listTareas: MutableList<Tareas> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val urlHomework = intent.getStringExtra("urlPlaneta")
        val urlSpecies = intent.getStringExtra("urlEspecie")
        val urlStarShips = intent.getStringExtra("urlStarShips")
        val urlVehicles = intent.getStringExtra("urlVehicles")


        val button = findViewById<FloatingActionButton>(R.id.bFormulario)
        button.setOnClickListener {
            val intent1 = Intent(this@MainActivity, FormularioTareaActivity::class.java)
            startActivity(intent1)
        }


        agregar()
        inflater()
    }

    fun agregar() {
        listTareas.add(
            Tareas
                (
                "Problemas con el inicio",
                "Av.Velazquez" + "\n" + "Málaga",
                "Mario Torrija",
                "Al iniciar la aplicación en un movil xiaomi la aplucacoón por una extraña razón se cierra automaticamente nada mas abrirla",
                "27/6/2019"
            )
        )
        listTareas.add(
            Tareas
                (
                "Problemas base de datos",
                "Calle Mauricio Moro Pareto " + "\n" + "Málaga",
                "Andrea Esteban",
                "No se quedan guardados los pedidos y estan intercambiados unos precios con otros",
                "27/6/2019"

            )
        )
        listTareas.add(
            Tareas
                (
                "Usuario erroneo",
                "Calle Obispo Hurtado" + "\n" + "Granada",
                "Paco Rodriguez",
                "Tengo un usuario creado con la aplicacón y no se guarda dicho usuario por extrañas razones",
                "27/6/2019"
            )
        )
        listTareas.add(
            Tareas
                (
                "La aplicación me parece racista",
                "Avenida Andalucia" + "\n" + "Jaen",
                "Ricardo Milos",
                "Simplemente me parece racista porque las letras son blancas y no negras ",
                "27/6/2019"

            )
        )
        listTareas.add(
            Tareas(
                "Fallo de conexión",
                ": Calle Medina" + "\n" + "Jerez de la frontera,Cadiz",
                "Camarón de la isla",
                "Fallo al hacer conexión con la base de datos",
                "27/6/2019"
            )
        )
    }

    fun inflater() {
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycleViewTareas.layoutManager = layoutManager
        val adapter = AdapterTareas(this@MainActivity, listTareas)
        recycleViewTareas.adapter = adapter

    }

}
