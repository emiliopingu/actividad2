package com.example.actividad2.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.util.UniversalTimeScale.toLong
import android.util.Log
import com.example.actividad2.items.Tareas

val DATABASE_NAME = "DB"
val ID = "_id"
val TABLE_NAME = "tareas"
val COLUMN_NOMBRE_TAREA = "nombreTarea"
val COLUMN_LUGAR = "lugarTarea"
val COLUMN_USUARIO = "usuario"
val COLUMN_FECHA = " fecducidad"
val COLUMN_DESCRIPCION = "descripcion"


class DataDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + "( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE_TAREA + " TEXT, " +
                COLUMN_LUGAR + " TEXT, " +
                COLUMN_USUARIO + " TEXT, " +
                COLUMN_FECHA + " TEXT, " +
                COLUMN_DESCRIPCION + " TEXT ); "
     
        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insert(tarea: List<Tareas>) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NOMBRE_TAREA, tarea[0].nombreTarea)
        values.put(COLUMN_LUGAR, tarea[0].lugarTarea)
        values.put(COLUMN_USUARIO, tarea[0].usuarioTarea)
        values.put(COLUMN_DESCRIPCION, tarea[0].descripcion)
        values.put(COLUMN_FECHA, tarea[0].fecducidad)
        var result = db.insert(TABLE_NAME, null, values)
        if (result == (-1).toLong()) {
            Log.i("funciona", "funciona")
        } else {
            Log.i("NO funciona", "NO funciona")
        }
    }

    /*fun getData(): MutableList<Tareas> {

        Table.tareas.items.clear()
        val columnas = arrayOf(
            Table.tareas.ID,
            Table.tareas.COLUMN_NOMBRE_TAREA,
            Table.tareas.COLUMN_LUGAR,
            Table.tareas.COLUMN_USUARIO,
            Table.tareas.COLUMN_DESCRIPCION,
            Table.tareas.COLUMN_FECHA
        )
        val c = db.query(Table.tareas.TABLE_NAME, columnas, null, null, null, null, null)

        if (c.moveToFirst()) {
            do {
                Table.tareas.items.add(
                    Tareas(
                        c.getInt(0), c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4), c.getString(5)
                    )
                )
            } while (c.moveToNext())
        }

        return Table.tareas.items
    }*/
}
