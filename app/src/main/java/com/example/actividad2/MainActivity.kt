package com.example.actividad2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
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
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.ViewGroup
import com.example.actividad2.api.RetrofitClient
import kotlinx.android.synthetic.main.tareas_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
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
        Log.i("log","http://127.0.0.1/webservice/insertarDatos.php/")
        Log.i("looog",RetrofitClient.service.recogerTarea().toString())

        setRecyclerViewItemTouchListener()
        recogerDatos()
        inflater()

        val button = findViewById<FloatingActionButton>(R.id.bFormulario)
        button.setOnClickListener {

            val view: View = layoutInflater.inflate(R.layout.activity_formulario_tarea, null)
            val builder = AlertDialog.Builder(this).setView(view)
            val showDialog = builder.show()


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
                    RetrofitClient.service.Insertartarea(nombre,lugar,usuario,descripcion,fecha).enqueue(object : Callback<Task> {
                        override fun onFailure(call: Call<Task>, t: Throwable) {
                           Log.i("fallo1","fallo la llamada")
                        }
                        override fun onResponse(call: Call<Task>, response: Response<Task>) {
                            Log.i("aciero","se realizo la llamada")
                            list.add(Task( nombre, lugar, usuario, descripcion, fecha.toString()))
                        }

                    })
                   // Repository(this).insertTask(nombre, lugar, usuario, descripcion, fecha.toString())

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
                    RetrofitClient.service.borrarTareas(nombre,lugar,usuario,descripcion,fecha).enqueue(object:Callback<Task>{
                        override fun onResponse(call: Call<Task>, response: Response<Task>) {
                            Log.i("aciero2","se realizo la llamada")
                            list.add(
                                Task( nombre, lugar, usuario, fecha, descripcion)
                            )
                        }

                        override fun onFailure(call: Call<Task>, t: Throwable) {
                            Log.i("fallo2","fallo la llamada")
                        }

                    })
                   // Repository(this).updateTask(nombre, lugar, usuario, fecha, descripcion)

                } else {
                    Toast.makeText(
                        this,
                        "La descripcion del problema debe de tener entre 100 y 10 caracteres",
                        Toast.LENGTH_LONG
                    ).show()
                }




        showDialog.dismiss()
        inflater()
    }
    view.cancelar.setOnClickListener{
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
    val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)
    recycleViewTareas.addItemDecoration(dividerItemDecoration)


}

/*fun consult() {

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
}*/
    private fun recogerDatos(){
    RetrofitClient.service.recogerTarea().enqueue(object :Callback<Task>{
        override fun onResponse(call: Call<Task>, response: Response<Task>) {
            if(response.isSuccessful){
                for (x in 0 until list.size){
                    val tarea=response.body()
                    if (tarea != null) {
                        list.add(Task(tarea.name,tarea.place,tarea.user,tarea.date,tarea.description))
                    }
                }

            }
        }

        override fun onFailure(call: Call<Task>, t: Throwable) {
      Log.i("falllo3","ha fallado en la conexion")
        }

    })
}


private fun setRecyclerViewItemTouchListener() {
    val helper = TaskHelper(this)
    val db = helper.writableDatabase

    val itemTouchCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean {

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {

                val position = viewHolder.adapterPosition

                    recycleViewTareas.adapter!!.notifyItemRemoved(position)
                    Repository(this@MainActivity).deleteTask(list[position].name)
//Cuandi es 0

                list.removeAt(position)
            }
        }

    val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
    itemTouchHelper.attachToRecyclerView(recycleViewTareas)
}
}

