package com.example.actividad2

import com.example.actividad2.data.LlamadaAPI
import com.example.actividad2.data.Task
import org.junit.Test

import org.junit.Assert.*

class MainActivityTest {

    @Test
    fun List() {
        val main = MainActivity()
        assertNotNull(main.list.add(Task("nomnre", "lugar", "usuario", "fecha", "descripcion", "fechacducidad")))
        assertNotNull(main.list.remove(Task("nomnre", "lugar", "usuario", "fecha", "descripcion", "fechacducidad")))
        for (x in 0 until main.list.size) {
            assertNotNull(main.list.removeAt(x))
        }
        assertNotNull(main.list)

    }

    @Test
    fun Llamada() {
        var llamada: LlamadaAPI? = LlamadaAPI()
        assertNotNull(llamada)
    }

}