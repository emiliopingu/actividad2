package com.example.actividad2

import org.jetbrains.anko.startActivity
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.*

class TestMain {

        private val main=MainActivity()
    val c = Calendar.getInstance()


    @Test
    fun testLlamadaApi(){
        assertNotNull(main.llamada)
    }

   /* @Test
    fun testCalendario(){
   Assert.assertNotNull(main.empezarAlarma(c))
    }*/


}