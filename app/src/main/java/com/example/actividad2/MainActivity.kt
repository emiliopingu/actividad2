package com.example.actividad2

import android.annotation.SuppressLint
import android.app.*
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast
import com.example.actividad2.data.Task
import com.example.actividad2.domain.TaskHelper
import com.example.actividad2.presentation.adapter.AdapterTareas
import kotlinx.android.synthetic.main.activity_formulario_tarea.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.formulario_actualizado.view.*
import java.util.*
import com.example.actividad2.api.RetrofitClient
import com.example.actividad2.data.LlamadaAPI
import com.example.actividad2.data.Receiver
import kotlinx.android.synthetic.main.elegir_tiempo.*
import kotlinx.android.synthetic.main.elegir_tiempo.view.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.Long.parseLong
import java.text.ParseException
import java.text.SimpleDateFormat


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    var list: MutableList<Task> = ArrayList()
    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "canal.1"
    private val description = "notificacion"
    private var continuar = true
    var llamada: LlamadaAPI? = LlamadaAPI()
    lateinit var alarmManager: AlarmManager
    lateinit var context: Context
    var callId = 3

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        inflater()
        setRecyclerViewItemTouchListener()


        val button = findViewById<FloatingActionButton>(R.id.bFormulario)
        button.setOnClickListener {

            val view: View = layoutInflater.inflate(R.layout.activity_formulario_tarea, null)
            val builder = AlertDialog.Builder(this).setView(view)
            val showDialog = builder.show()
//fechaInicial
            view.tvDate.setOnClickListener {

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

                        view.tvDate.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    }

                // create picker
                val datePicker = DatePickerDialog(this, listener, year, month, day)
                datePicker.show()
            }
            //fechaCaducidad
            view.tvDateCaducidad.setOnClickListener {

                val currentDate = Calendar.getInstance()
                var year = currentDate.get(Calendar.YEAR)
                var month = currentDate.get(Calendar.MONTH)
                var day = currentDate.get(Calendar.DAY_OF_MONTH)

                if (view.tvDateCaducidad.text.isNotEmpty()) {
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

                        view.tvDateCaducidad.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    }

                // create picker
                val datePicker = DatePickerDialog(this, listener, year, month, day)
                datePicker.show()
            }

            /**
             * clicar el boton donde se envia la informacion al servidor y se crea la notificacion
             */
            view.bFormularioEnviar.setOnClickListener {
                val nombre = view.etNombreProblema.text.toString()
                val lugar = view.etLugarTarea.text.toString()
                val usuario = view.etPersonaTarea.text.toString()
                val descripcion = view.etDescripcionTarea.text.toString()
                val fecha = view.tvDate.text.toString()
                val fechaCaducidad = view.tvDateCaducidad.text.toString()

                if (!nombre.isEmpty() && !lugar.isEmpty() && !usuario.isEmpty()
                    && !descripcion.isEmpty() && !fecha.isEmpty() && !fechaCaducidad.isEmpty()
                ) {
                    val view: View = layoutInflater.inflate(R.layout.elegir_tiempo, null)
                    val builder2 = AlertDialog.Builder(this).setView(view)
                    val showDialog = builder2.show()
                    view.tvCaducidad.setOnClickListener {
                        val currentDate = Calendar.getInstance()
                        var year = currentDate.get(Calendar.YEAR)
                        var month = currentDate.get(Calendar.MONTH)
                        var day = currentDate.get(Calendar.DAY_OF_MONTH)


                        if (view.tvCaducidad.text.isNotEmpty()) {
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
                                val intent = Intent(Intent.ACTION_EDIT)
                                intent.type = "vnd.android.cursor.item/event"

                                intent.putExtra(CalendarContract.Events.DTSTART, currentDate.timeInMillis)
                                intent.putExtra(
                                    CalendarContract.Events.DTEND,
                                    currentDate.timeInMillis * 60 * 60 * 1000
                                )
                                intent.putExtra(CalendarContract.Events.TITLE, nombre)
                                intent.putExtra(CalendarContract.Events.DESCRIPTION, descripcion)
                                intent.putExtra(CalendarContract.Events.CALENDAR_ID, callId)
                                intent.putExtra(CalendarContract.Events.EVENT_TIMEZONE, "España/Madrid")
                                intent.putExtra(
                                    CalendarContract.Events.ACCESS_LEVEL,
                                    CalendarContract.Events.ACCESS_PRIVATE
                                )
                                intent.putExtra(
                                    CalendarContract.Events.AVAILABILITY,
                                    CalendarContract.Events.AVAILABILITY_BUSY
                                )


                                startActivity(intent)
                                view.tvCaducidad.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"

                            }

                        // create picker
                        val datePicker = DatePickerDialog(this, listener, year, month, day)
                        datePicker.show()

                        try {
                            llamada?.llamadaParaInsertar(nombre, lugar, usuario, fecha, descripcion, fechaCaducidad)
                            Toast.makeText(this@MainActivity, "se ha guardado los datos", Toast.LENGTH_LONG).show()
                        } catch (e: IOException) {
                            Toast.makeText(this@MainActivity, "No se ha guardado los datos", Toast.LENGTH_LONG).show()

                        }
                        Toast.makeText(this@MainActivity, "se ha guardado los datos", Toast.LENGTH_LONG).show()

                        empezarAlarma(currentDate)
                    }

                    view.enviarNotificacion.setOnClickListener {

                        showDialog.dismiss()
                    }
                    /**Inserta x valores en la base de datos
                     * @param nombre
                     * @param lugar
                     * @param usuario
                     * @param fecha
                     * @param descripcion
                     *@param fechaCaducidad
                     */

                    list.add(Task(nombre, lugar, usuario, fecha, descripcion, fechaCaducidad))


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
        /** Accion donde se llama a layour bEliminar .Tiene incorporado un dayPickerDialog
         *
         */
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


            /** Accion cuando se pulse bActualizar
             *
             */
            view.bActualizarT.setOnClickListener {
                //Pasar la informacion editText a String
                val nombre = view.etNombreProblemaEdit.text.toString()
                val lugar = view.etLugarTareaEdit.text.toString()
                val usuario = view.etPersonaTareaEdit.text.toString()
                val fecha = view.editDate.text.toString()
                val descripcion = view.etDescripcionTareaEdit.text.toString()


                if (!nombre.isEmpty() && !lugar.isEmpty() && !usuario.isEmpty()
                    && !descripcion.isEmpty() && !fecha.isEmpty()
                ) {
                    if (llamada!!.llamadaParaActualizar(
                            nombre,
                            lugar,
                            usuario,
                            fecha,
                            descripcion,
                            "pepe2.1"
                        ) != null
                    ) {
                        try{
                            Toast.makeText(this@MainActivity, "Se ha actualizado la tarea", Toast.LENGTH_LONG)
                        }catch (e: IOException){
                            Toast.makeText(this@MainActivity, "No se ha actualizado la tarea", Toast.LENGTH_LONG)
                        }

                    }


                    for (x in 0 until list.size) {
                        if (list[x].name == nombre) {
                            list.removeAt(x)
                        }
                    }

                    inflater()


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
            view.cancelar.setOnClickListener {
                showDialog.dismiss()
            }


        }

    }

    /** Funcion para descargar los datos en el adapter y hacerlos visibles donde se le pasa una lista y un recycleView
     *
     */
    fun inflater() {
        llamada?.callApi(list)
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
    /**funcion para recoger datos de la API
     */


    /** Funcion para borrar las tareas arrastrandolas gracias al itemTouchCallBack
     *
     */

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
                    // Repository(this@MainActivity).deleteTask(list[position].name)
                    // Repository(this@MainActivity).deleteTask(list[position].name)
                    try {
                        llamada?.llamadaParaEliminar(list[position].name.toString())
                        Toast.makeText(this@MainActivity, "Se ha eliminado la tarea", Toast.LENGTH_LONG)
                    } catch (e: IOException) {
                        Toast.makeText(this@MainActivity, "No se ha eliminado la tarea", Toast.LENGTH_LONG)
                    }


                    list.removeAt(position)




                    inflater()

                }
            }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recycleViewTareas)
    }




    fun empezarAlarma(c: Calendar) {

        val intent = Intent(this, Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1)
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.timeInMillis, pendingIntent)
        Log.i("alarma", "la alarma ha empezado")
    }


}

