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
            "CREATE TABLE " + Table.items.TABLE_NAME + "( " +
                    Table.items.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Table.items.COLUMN_NOMBRE_TAREA + " TEXT, " +
                    Table.items.COLUMN_LUGAR + " TEXT, " +
                    Table.items. COLUMN_USUARIO + " TEXT, " +
                    Table.items.COLUMN_FECHA + " TEXT, " +
                    Table.items.COLUMN_DESCRIPCION + " TEXT ); "

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    fun insert(tarea: List<Tareas>) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(Table.items.COLUMN_NOMBRE_TAREA, tarea[0].nombreTarea)
        values.put(Table.items.COLUMN_LUGAR, tarea[0].lugarTarea)
        values.put(Table.items.COLUMN_USUARIO, tarea[0].usuarioTarea)
        values.put(Table.items.COLUMN_DESCRIPCION, tarea[0].descripcion)
        values.put(Table.items.COLUMN_FECHA, tarea[0].fecducidad)
        var result = db.insert(Table.items.TABLE_NAME, null, values)
        if (result == (-1).toLong()) {
            Log.i("funciona", "funciona")
        } else {
            Log.i("NO funciona", "NO funciona")
        }
    }

    fun getData(): MutableList<Tareas> {

        Table.items.tarea.clear()
        val columnas = arrayOf(
            Table.items.ID,
            Table.items.COLUMN_NOMBRE_TAREA,
            Table.items.COLUMN_LUGAR,
            Table.items.COLUMN_USUARIO,
            Table.items.COLUMN_DESCRIPCION,
            Table.items.COLUMN_FECHA
        )
        val c = db.query(Table.items.TABLE_NAME, columnas, null, null, null, null, null)

        if (c.moveToFirst()) {
            do {
                Table.items.tarea.add(
                    Tareas(
                        c.getInt(0), c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4), c.getString(5)
                    )
                )
            } while (c.moveToNext())
        }

        return Table.items.tarea
}}
