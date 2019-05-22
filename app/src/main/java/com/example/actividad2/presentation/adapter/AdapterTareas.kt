package com.example.actividad2.presentation.adapter

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.actividad2.R
import com.example.actividad2.data.Table
import com.example.actividad2.presentation.DescripcionTareaActivity
import com.example.actividad2.domain.DataDbHelper
import com.example.actividad2.data.items.Tareas
import kotlinx.android.synthetic.main.tareas_list.view.*

class AdapterTareas(val context: Context, var cursor: Cursor) :
    RecyclerView.Adapter<AdapterTareas.viewHolder>() {

    private var db: DataDbHelper? = null
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tareas_list, parent, false)
        return viewHolder(view)

    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val nombre = cursor.getString(cursor.getColumnIndex(Table.items.COLUMN_NOMBRE_TAREA))
        val lugar = cursor.getString(cursor.getColumnIndex(Table.items.COLUMN_LUGAR))
        val usuario = cursor.getString(cursor.getColumnIndex(Table.items.COLUMN_USUARIO))
        val descripcion = cursor.getString(cursor.getColumnIndex(Table.items.COLUMN_DESCRIPCION))
        val fecha = cursor.getString(cursor.getColumnIndex(Table.items.COLUMN_FECHA))

        holder.view.tvNombreTarea.text = nombre
        holder.view.tvLugarTarea.text = lugar
        holder.view.tvUsuarioTarea.text = usuario



        holder.view.setOnClickListener {
            val intent1 = Intent(context, DescripcionTareaActivity::class.java)
            intent1.putExtra("tareaDescripcion", nombre)
            intent1.putExtra("usuarioDescripcion", usuario)
            intent1.putExtra("problemaDescripcion", descripcion)
            intent1.putExtra("lugarDescripcion", lugar)
            intent1.putExtra("fechaDescripcion", fecha)
            context.startActivity(intent1)
        }
    }

    override fun getItemCount(): Int {
        return cursor.count
    }

    fun swapCursor(cursorNuevo: Cursor) {
        if (cursor != null) {
            cursor.close()
        }

        cursor = cursorNuevo
        if (cursorNuevo != null) {
            notifyDataSetChanged()
        }
    }

    inner class viewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
