package com.example.actividad2.data

class Table {
    abstract class Tareas {
        companion object {
            val ID = "_id"
            val TABLE_NAME = "tareas"
            val COLUMN_NOMBRE_TAREA = "nombreTarea"
            val COLUMN_LUGAR = "lugarTarea"
            val COLUMN_USUARIO = "usuario"
            val COLUMN_FECHA = " fecducidad"
            val COLUMN_DESCRIPCION = "descripcion"
            var tarea: MutableList<Tareas> = ArrayList()
        }
    }
}