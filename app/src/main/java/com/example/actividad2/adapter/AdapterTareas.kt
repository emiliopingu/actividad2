package com.example.actividad2.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.actividad2.R
import com.example.actividad2.activitys.DescripcionTareaActivity
import com.example.actividad2.item.Tareas
import kotlinx.android.synthetic.main.tareas_list.view.*

class AdapterTareas(val context: Context, val conjuntoTareas: MutableList<Tareas>) :
    RecyclerView.Adapter<AdapterTareas.viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tareas_list, parent, false)
        return viewHolder(view)
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val tarea = conjuntoTareas[position]
        holder.view.tvNombreTarea.text = tarea.nombreTarea
        holder.view.tvLugarTarea.text = tarea.lugarTarea
        holder.view.tvUsuarioTarea.text = tarea.usuarioTarea

        holder.view.setOnClickListener {
            val intent1 = Intent(context, DescripcionTareaActivity::class.java)
            intent1.putExtra("tareaDescripcion", tarea.descripcion)
            intent1.putExtra("usuarioDescripcion",tarea.usuarioTarea)
            intent1.putExtra("problemaDescripcion",tarea.nombreTarea)
            intent1.putExtra("lugarDescripcion",tarea.lugarTarea)
            intent1.putExtra("fechaDescripcion",tarea.fecducidad)
            context.startActivity(intent1)
        }
    }

    override fun getItemCount(): Int {
        return conjuntoTareas.size
    }

    inner class viewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
