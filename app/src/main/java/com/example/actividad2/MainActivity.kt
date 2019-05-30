package com.example.actividad2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
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

import java.util.*


class MainActivity : AppCompatActivity() {

    var list: MutableList<Task> = ArrayList()
    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0


    @SuppressLint("SetTextI18n")
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


            val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onSwiped(view1: RecyclerView.ViewHolder, position: Int) {
                    list.removeAt(view1.adapterPosition)
                    val l = list[position]
                    Repository(this@MainActivity).deleteTask(l.name)
                }

                override fun onMove(
                    p0: RecyclerView,
                    p1: RecyclerView.ViewHolder,
                    p2: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }
            }
            Log.i("eliminarItem", "hola$itemTouchHelper")

            view.tvDate.setOnClickListener {
                // get current date
                val currentDate = Calendar.getInstance()
                var year = currentDate.get(Calendar.YEAR)
                var month = currentDate.get(Calendar.MONTH)
                var day = currentDate.get(Calendar.DAY_OF_MONTH)

                if (view.tvDate.text.isNotEmpty()) {
                    year = this.selectedYear
                    month = this.selectedMonth
                    day = this.selectedDay
                }

                // create listener
                val listener =
                    DatePickerDialog.OnDateSetListener { datePicker, selectedYear, selectedMonth, selectedDay ->
                        this.selectedYear = selectedYear
                        this.selectedMonth = selectedMonth
                        this.selectedDay = selectedDay

                        view.tvDate.text = "${selectedMonth + 1}/$selectedDay/$selectedYear"
                    }

                // create picker
                val datePicker = DatePickerDialog(this, listener, year, month, day)
                datePicker.show()
            }

            view.bFormularioEnviar.setOnClickListener {
                val nombre = view.etNombreProblema.text.toString()
                val lugar = view.etLugarTarea.text.toString()
                val usuario = view.etPersonaTarea.text.toString()
                val descripcion = view.etDescripcionTarea.text.toString()
                val fecha = view.tvDate.text.toString()

                if (!nombre.isEmpty() && !lugar.isEmpty() && !usuario.isEmpty()
                    && !descripcion.isEmpty() && !fecha.isEmpty()
                ) {
                    Repository(this).insertTask(nombre, lugar, usuario, descripcion, fecha.toString())
                    list.add(Task(0, nombre, lugar, usuario, descripcion, fecha.toString()))
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

            view.editDate.setOnClickListener {
                // get current date
                val currentDate = Calendar.getInstance()
                var year = currentDate.get(Calendar.YEAR)
                var month = currentDate.get(Calendar.MONTH)
                var day = currentDate.get(Calendar.DAY_OF_MONTH)

                if (view.editDate.text.isNotEmpty()) {
                    year = this.selectedYear
                    month = this.selectedMonth
                    day = this.selectedDay
                }

                // create listener
                val listener =
                    DatePickerDialog.OnDateSetListener { datePicker, selectedYear, selectedMonth, selectedDay ->
                        this.selectedYear = selectedYear
                        this.selectedMonth = selectedMonth
                        this.selectedDay = selectedDay

                        view.editDate.text = "${selectedMonth + 1}/$selectedDay/$selectedYear"
                    }

                // create picker
                val datePicker = DatePickerDialog(this, listener, year, month, day)
                datePicker.show()
            }



            view.bActualizarT.setOnClickListener {
                val nombre = view.etNombreProblemaEdit.text.toString()
                val lugar = view.etLugarTareaEdit.text.toString()
                val usuario = view.etPersonaTareaEdit.text.toString()
                val fecha = view.editDate.text.toString()
                val descripcion = view.etDescripcionTareaEdit.text.toString()

                if (!nombre.isEmpty() && !lugar.isEmpty() && !usuario.isEmpty()
                    && !descripcion.isEmpty() && !fecha.isEmpty()
                ) {
                    for (x in 0 until list.size) {
                        if (list[x].name == nombre) {
                            list.removeAt(x)
                        }
                    }
                    Repository(this).updateTask(nombre, lugar, usuario, fecha, descripcion)
                    list.add(
                        Task(0, nombre, lugar, usuario, fecha, descripcion)

                    )
                }




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

