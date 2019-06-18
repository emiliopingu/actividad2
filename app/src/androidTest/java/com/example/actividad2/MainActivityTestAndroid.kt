package com.example.actividad2

import android.app.AlarmManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.actividad2.data.Task
import com.example.actividad2.presentation.adapter.AdapterTareas
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class MainActivityTestAndroid {

    val appContext = InstrumentationRegistry.getTargetContext()


    @Test
    fun AlarmManager() {
        lateinit var alarmManager: AlarmManager
        assertNotNull(alarmManager)
    }

    @Before
    fun Context() {
        assertNotNull(appContext)
    }




    @Test
    fun empezarAlarma() {
        val c:Calendar= Calendar.getInstance()
        assertNotNull(c)
       val m= MainActivity()
        assertNotNull(m.empezarAlarma(c))


    }
}