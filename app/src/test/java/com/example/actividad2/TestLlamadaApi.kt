package com.example.actividad2

import com.example.actividad2.data.LlamadaAPI
import org.junit.Test

import org.junit.Assert.*


class TestLlamadaApi {

    private val llamada=LlamadaAPI()

    val nombre="Problemas conexion"
    val lugar="cabrera"
    val usuario="Paco"
    val descripcion="Esto no va"
    val fecha="08/06/2019"
    val fechaCaducidad="20/06/2019"
    @Test
    fun insertarDatos() {
        assertNotNull(llamada.llamadaParaInsertar(nombre,lugar,usuario,descripcion,fecha,fechaCaducidad))
    }

    @Test
    fun actualizarDatos(){
        assertNotNull(llamada.llamadaParaActualizar(nombre,lugar,usuario,descripcion,fecha,fechaCaducidad))
    }

    @Test
    fun eliminarDatos(){
        assertNotNull(llamada.llamadaParaEliminar(nombre))
    }


}
