package com.example.actividad2

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.actividad2.data.ReadTask
import com.example.actividad2.data.Task
import com.example.actividad2.domain.Repository
import com.example.actividad2.domain.TaskHelper
import com.example.actividad2.presentation.adapter.AdapterTareas
import kotlinx.android.synthetic.main.activity_formulario_tarea.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.formulario_actualizado.view.*

import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {

    var list: MutableList<Task> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val helper = TaskHelper(this)
        val db = helper.writableDatabase
        consult()
        inflater()

        val button = findViewById<FloatingActionButton>(R.id.bFormulario)
        button.setOnClickListener {

            val view: View = layoutInflater.inflate(R.layout.activity_formulario_tarea, null)
            val builder = AlertDialog.Builder(this).setView(view)
            val showDialog = builder.show()

            view.bFormularioEnviar.setOnClickListener {
                val nombre = view.etNombreProblema.text.toString()
                val lugar = view.etLugarTarea.text.toString()
                val usuario = view.etPersonaTarea.text.toString()
                val descripcion = view.etDescripcionTarea.text.toString()
                val fecha = view.etFechaTarea.text.toString()

                if (!nombre.isEmpty() && !lugar.isEmpty() && !usuario.isEmpty()
                    && !descripcion.isEmpty() && !fecha.isEmpty()
                ) {
                    Repository(this).insertTask(nombre, lugar, usuario, descripcion, fecha)
                    list.add(Task(0, nombre, lugar, usuario, descripcion, fecha))
                    Toast.makeText(this@MainActivity, "se ha guardado los datos", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "Rellene los campos", Toast.LENGTH_LONG).show()
                }

                showDialog.dismiss()
                inflater()

            }

            view.buttonCancel.setOnClickListener {
                showDialog.dismiss()
            }
        }

        val buttonEliminar = findViewById<FloatingActionButton>(R.id.bEliminar)
        buttonEliminar.setOnClickListener {
            val view: View = layoutInflater.inflate(R.layout.formulario_actualizado, null)
            val builder = AlertDialog.Builder(this).setView(view)
            val showDialog = builder.show()

            view.bActualizarT.setOnClickListener {
                val nombre = view.etNombreProblemaEdit.text.toString()
                val lugar = view.etLugarTareaEdit.text.toString()
                val usuario = view.etPersonaTareaEdit.text.toString()
                val fecha = view.etFechaTareaEdit.text.toString()
                val descripcion = view.etDescripcionTareaEdit.text.toString()

                if (!nombre.isEmpty() && !lugar.isEmpty() && !usuario.isEmpty()
                    && !descripcion.isEmpty() && !fecha.isEmpty()
                ) {
                    for(x in 0  until list.size) {
                        if (list[x].name == nombre) {
                            list.removeAt(x)
                        }
                    }
                    Repository(this).updateTask(nombre, lugar, usuario, fecha, descripcion)
                    list.add(Task(0,nombre,lugar,usuario,fecha,descripcion)

                    )
                }


                /*val nombre = view.etEliminar.text.toString()
                if (!nombre.isEmpty()) {
                    Repository(this).deleteTask(nombre)
                    for(x in 0  until list.size){
                        if(list[x].name==nombre){
                            list.removeAt(x)
                        }

                    }

                    Toast.makeText(applicationContext, "El usuario Fue Eliminado", Toast.LENGTH_LONG).show()

                }*/

                showDialog.dismiss()
                inflater()
            }
            view.cancelar.setOnClickListener {
                showDialog.dismiss()
            }


        }

    }


    fun inflater() {

        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycleViewTareas.layoutManager = layoutManager
        val adapter = AdapterTareas(this@MainActivity, list)
        recycleViewTareas.adapter = adapter

    }


    fun consult() {

        val helper = TaskHelper(this)
        val db = helper.writableDatabase
        val cursor: Cursor = db.rawQuery(" SELECT * FROM " + ReadTask.Entry.TABLE_NAME, null)
        while (cursor.moveToNext()) {

            val name: String = cursor.getString(1)
            val place: String = cursor.getString(2)
            val user: String = cursor.getString(3)
            val datee: String = cursor.getString(4)
            val description: String = cursor.getString(5)
            list.add(Task(0, name, place, user, datee, description))

        }
    }

}
