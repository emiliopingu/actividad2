package com.example.actividad2.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.actividad2.MainActivity
import com.example.actividad2.api.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList



class LlamadaAPI {

    fun llamadaParaEliminar(nombre:String){
        RetrofitClient.service.deleteTask(nombre)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.i("llamada6", "eliminado")
                    if (response.isSuccessful) {
           // Toast.makeText(main.applicationContext,"Se ha eliminado",Toast.LENGTH_LONG)
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("llamada6", "La llamada a la api no ha funcionado")
                 //   Toast.makeText(main.applicationContext,"No se ha eliminado",Toast.LENGTH_LONG)
                }


            })
    }
    fun llamadaParaActualizar(nombre:String,lugar:String,usuario:String,fecha:String,descripcion:String,fechaCaducidad:String){
        RetrofitClient.service.updateTask(nombre, lugar, usuario, fecha, descripcion, fechaCaducidad)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.i("aciero2", "se ha acttualizado")
                  //  Toast.makeText(main.applicationContext,"Se ha actualizado",Toast.LENGTH_LONG)

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("fallo2", "fallo la llamada")
                   // Toast.makeText(main.applicationContext,"No se ha actualizado",Toast.LENGTH_LONG)
                }

            })
    }
    fun callApi(list: MutableList<Task> = ArrayList()) {
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

                }

            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                Log.i("llamada2", "La llamada a la api no ha funcionado")
            }


        })
    }
    fun llamadaParaInsertar(nombre: String,lugar: String,usuario: String,fecha: String,descripcion: String,fechaCaducidad: String){
        RetrofitClient.service.insertTask(nombre, lugar, usuario, fecha, descripcion, fechaCaducidad)
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("fallo1", "fallo la llamada")
                  //  Toast.makeText(main.applicationContext,"No se ha insertado",Toast.LENGTH_LONG)
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.i("aciero", "se realizo la llamada")
                  //  Toast.makeText(main.applicationContext,"se ha insertado",Toast.LENGTH_LONG)



                }

            })
    }

}