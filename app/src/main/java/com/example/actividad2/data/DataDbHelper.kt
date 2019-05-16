package com.example.actividad2.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.actividad2.items.Tareas

class DataDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private val db: SQLiteDatabase
    private val values: ContentValues

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "tareas"
    }

    init {
        db = this.writableDatabase
        values = ContentValues()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            "CREATE TABLE " + Table.item.TABLE_NAME + " (" +
                    Table.item.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Table.item.COLUMN_NOMBRE_TAREA + "TEXT NOT NULL," +
                    Table.item.COLUMN_USUARIO + "TEXT NOT NULL," +
                    Table.item.COLUMN_LUGAR + "TEXT NOT NULL," +
                    Table.item.COLUMN_DESCRIPCION + "TEXT NOT NULL," +
                    Table.item.COLUMN_FECHA + "TEXT NOT NULL)");

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insert(items:List<Tareas>){
        values.put(Table.item.COLUMN_NOMBRE_TAREA,items[0].nombreTarea)
        values.put(Table.item.COLUMN_USUARIO,items[0].usuarioTarea)
        values.put(Table.item.COLUMN_LUGAR,items[0].lugarTarea)
        values.put(Table.item.COLUMN_DESCRIPCION,items[0].descripcion)
        values.put(Table.item.COLUMN_FECHA,items[0].fecducidad)
        db.insert(Table.item.TABLE_NAME,null,values)
    }

}