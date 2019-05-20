package com.example.actividad2.domain

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.actividad2.data.Table
import com.example.actividad2.data.items.Tareas


class DataDbHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME, null,
    DATABASE_VER
) {

    private val db: SQLiteDatabase
    private val values: ContentValues

    companion object {
        private val DATABASE_NAME = "DB"
        private val DATABASE_VER = 1

    }

    init {
        db = this.writableDatabase
        values = ContentValues()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE " + Table.items.TABLE_NAME + "( " +
                    Table.items.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Table.items.COLUMN_NOMBRE_TAREA + " TEXT, " +
                    Table.items.COLUMN_LUGAR + " TEXT, " +
                    Table.items.COLUMN_USUARIO + " TEXT, " +
                    Table.items.COLUMN_FECHA + " TEXT, " +
                    Table.items.COLUMN_DESCRIPCION + "TEXT ); "

        db?.execSQL(createTable)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    fun insert(tarea: MutableList<Tareas>) {
        val db = this.writableDatabase
        val values = ContentValues()
        for(i in 0..tarea.size){
            values.put(Table.items.ID, tarea[i].id)
            values.put(Table.items.COLUMN_NOMBRE_TAREA, tarea[0].nombreTarea)
            values.put(Table.items.COLUMN_LUGAR, tarea[0].lugarTarea)
            values.put(Table.items.COLUMN_USUARIO, tarea[0].usuarioTarea)
            values.put(Table.items.COLUMN_DESCRIPCION, tarea[0].descripcion)
            values.put(Table.items.COLUMN_FECHA, tarea[0].fecducidad)
        }

        var result = db.insert(Table.items.TABLE_NAME, null, values)
        if (result == (-1).toLong()) {
            Log.i("funciona", "funciona")
        } else {
            Log.i("NO funciona", "NO funciona")
        }
    }

    fun getData(): MutableList<Tareas> {

        val list: MutableList<Tareas> = ArrayList()
        val db: SQLiteDatabase = this.readableDatabase
        val query = "SELECT * FROM " + Table.items.TABLE_NAME
        val c = db.rawQuery(query, null)

        if (c.moveToFirst()) {
            do {
                var tarea = Tareas()
                tarea.id = c.getString(c.getColumnIndex(Table.items.COLUMN_NOMBRE_TAREA)).toInt()
                tarea.nombreTarea = c.getString(c.getColumnIndex(Table.items.COLUMN_NOMBRE_TAREA))
                tarea.lugarTarea = c.getString(c.getColumnIndex(Table.items.COLUMN_NOMBRE_TAREA))
                tarea.usuarioTarea = c.getString(c.getColumnIndex(Table.items.COLUMN_NOMBRE_TAREA))
                tarea.descripcion = c.getString(c.getColumnIndex(Table.items.COLUMN_NOMBRE_TAREA))
                tarea.fecducidad = c.getString(c.getColumnIndex(Table.items.COLUMN_NOMBRE_TAREA))

            } while (c.moveToNext())
        }

        c.close()
        db.close()
        return list
    }

    fun getDelete() {
        val db = this.writableDatabase
        db.delete(Table.items.TABLE_NAME, null, null)
        db.close()

    }
}
