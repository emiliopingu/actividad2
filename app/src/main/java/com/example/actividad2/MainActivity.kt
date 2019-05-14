package com.example.actividad2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.actividad2.adapter.AdapterTareas
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun inflater(){
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycleViewTareas.layoutManager = layoutManager
        val adapter = AdapterTareas(this@MainActivity)
        recycleViewTareas.adapter = adapter

    }
}
