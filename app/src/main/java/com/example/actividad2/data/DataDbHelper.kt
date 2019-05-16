package com.example.actividad2.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.util.UniversalTimeScale.toLong
import android.util.Log
import com.example.actividad2.items.Tareas




class DataDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {

    private val db: SQLiteDatabase
    private val values: ContentValues

    companion object {
        private val DATABASE_NAME = "DB"
        private val DATABASE_VER = 1

    }
    init {
        db=this.writableDatabase
        values= ContentValues()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE " + Table.Tareas.TABLE_NAME + "( " +
                    Table.Tareas.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Table.Tareas.COLUMN_NOMBRE_TAREA + " TEXT, " +
                    Table.Tareas.COLUMN_LUGAR + " TEXT, " +
                    Table.Tareas. COLUMN_USUARIO + " TEXT, " +
                    Table.Tareas.COLUMN_FECHA + " TEXT, " +
                    Table.Tareas.COLUMN_DESCRIPCION + " TEXT ); "

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    fun insert(tarea: List<Tareas>) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(Table.Tareas.COLUMN_NOMBRE_TAREA, tarea[0].nombreTarea)
        values.put(Table.Tareas.COLUMN_LUGAR, tarea[0].lugarTarea)
        values.put(Table.Tareas.COLUMN_USUARIO, tarea[0].usuarioTarea)
        values.put(Table.Tareas.COLUMN_DESCRIPCION, tarea[0].descripcion)
        values.put(Table.Tareas.COLUMN_FECHA, tarea[0].fecducidad)
        var result = db.insert(Table.Tareas.TABLE_NAME, null, values)
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
