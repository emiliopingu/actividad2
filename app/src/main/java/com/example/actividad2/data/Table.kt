package com.example.actividad2.data

import com.example.actividad2.data.items.Tareas

class Table {
    abstract class items {
        companion object {
            const val ID = "_id"
            const val TABLE_NAME = " tareas"
            const val COLUMN_NOMBRE_TAREA = " nombreTarea"
            const val COLUMN_LUGAR = " lugarTarea"
            const val COLUMN_USUARIO = " usuarioTarea"
            const val COLUMN_FECHA = " fecducidad"
            const val COLUMN_DESCRIPCION = " descripcion"
            //var tarea: MutableList<Tareas> = ArrayList()
        }
    }
}