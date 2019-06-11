package com.example.actividad2

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
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
import kotlinx.android.synthetic.main.elegir_tiempo.*
import kotlinx.android.synthetic.main.elegir_tiempo.view.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        inflater()
        callApi()
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

                                view.tvCaducidad.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                            }

                        // create picker
                        val datePicker = DatePickerDialog(this, listener, year, month, day)
                        datePicker.show()
                    }

                    view.enviarNotificacion.setOnClickListener {
                        val fechaUsuario = view.tvCaducidad.text.toString()
                        val formatter = SimpleDateFormat("dd/MM/yyyy")
                        val tiempo = diferenciaDeFechas(fecha, fechaCaducidad)

                        try {

                            val dateUsuario = formatter.parse(fechaUsuario)
                            val fechaLong = Math.abs(dateUsuario.time)

                            Thread {
                                while (continuar) {
                                    for (x in 0 until tiempo) {
                                        Thread.sleep(tiempo)
                                        if (x == fechaLong) {
                                            continuar = false
                                        }
                                    }
                                }
                                if (!continuar) {
                                    notificacion(nombre, ((tiempo - fechaLong) / (60 * 60 * 1000)).toInt())
                                }

                            }
                        } catch (e: ParseException) {
                            e.printStackTrace()
                        }




                        showDialog.dismiss()
                    }

                    RetrofitClient.service.insertTask(nombre, lugar, usuario, fecha, descripcion, fechaCaducidad)
                        .enqueue(object : Callback<ResponseBody> {
                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Log.i("fallo1", "fallo la llamada")
                            }

                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                Log.i("aciero", "se realizo la llamada")


                            }

                        })
                    list.add(Task(nombre, lugar, usuario, fecha, descripcion, fechaCaducidad))



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
                val fechaCaducidad = view.tvDateCaducidad.text.toString()

                if (!nombre.isEmpty() && !lugar.isEmpty() && !usuario.isEmpty()
                    && !descripcion.isEmpty() && !fecha.isEmpty() && !fechaCaducidad.isEmpty()
                ) {


                    RetrofitClient.service.updateTask(nombre, lugar, usuario, fecha, descripcion, fechaCaducidad)
                        .enqueue(object : Callback<ResponseBody> {
                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                Log.i("aciero2", "se realizo la llamada")


                                Toast.makeText(this@MainActivity, "Datos actualizados", Toast.LENGTH_LONG).show()
                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Log.i("fallo2", "fallo la llamada")
                            }

                        })
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
    fun callApi() {
        RetrofitClient.service.getTask().enqueue(object : Callback<List<Task>> {
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                Log.i("llamada1", "La llamada a la api ha funcionado")
                if (response.isSuccessful) {
                    val lista = response.body()
                    for (x in 0 until lista!!.size) {
                        list.add(
                            Task(
                                lista[x].name,
                                lista[x].place,
                                lista[x].user,
                                lista[x].date,
                                lista[x].description,
                                lista[x].fechaDeCaducidad
                            )
                        )


                    }
                    inflater()
                }

            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                Log.i("llamada2", "La llamada a la api no ha funcionado")
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
                    // Repository(this@MainActivity).deleteTask(list[position].name)
                    // Repository(this@MainActivity).deleteTask(list[position].name)
                    RetrofitClient.service.deleteTask(list[position].name.toString())
                        .enqueue(object : Callback<ResponseBody> {
                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                Log.i("llamada6", "eliminado")
                                if (response.isSuccessful) {

                                    Toast.makeText(this@MainActivity, "Ha sido eliminado", Toast.LENGTH_LONG).show()
                                }

                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Log.i("llamada6", "La llamada a la api no ha funcionado")
                            }


                        })
                    list.removeAt(position)
                    inflater()

                }
            }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recycleViewTareas)
    }


    fun notificacion(nombre: String, horas: Int) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val contentView = RemoteViews(packageName, R.layout.notificacion)
        contentView.setTextViewText(R.id.tv_title, "NOTIFICACION FECHA DE CADUCIDAD")
        contentView.setTextViewText(R.id.tv_content, "Ha la tarea " + nombre + " le queda " + horas + "horas")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setContent(contentView)
                .setSmallIcon(R.drawable.navigation_empty_icon)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.navigation_empty_icon))
                .setContentIntent(pendingIntent)
        } else {

            builder = Notification.Builder(this)
                .setContent(contentView)
                .setSmallIcon(R.drawable.navigation_empty_icon)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.navigation_empty_icon))
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())

    }

    fun diferenciaDeFechas(fechaInicio: String, fechaFinal: String): Long {
        var diferenciaDeTiempo: Long = 0
        val formatter = SimpleDateFormat("dd/MM/yyyy")


        try {

            val dateInicio = formatter.parse(fechaInicio)
            val dateFinal = formatter.parse(fechaFinal)
            //Parceas tus fechas en string a variables de tipo date se agrega un try catch porque si el formato declarado anteriormente no es igual a tu fecha obtendrás una excepción


            //obtienes la diferencia de las fechas
            diferenciaDeTiempo = Math.abs(dateInicio.time - dateFinal.time);

            //obtienes la diferencia en horas ya que la diferencia anterior esta en segundos

            Log.e("Difference: ", diferenciaDeTiempo.toString())


        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return diferenciaDeTiempo


    }
}

