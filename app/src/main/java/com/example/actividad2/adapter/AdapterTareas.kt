package com.example.actividad2.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.actividad2.R

class AdapterTareas(val context: Context) : RecyclerView.Adapter<AdapterTareas.viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tareas_list, parent, false)
        return viewHolder(view)
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 4
    }

    inner class viewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
