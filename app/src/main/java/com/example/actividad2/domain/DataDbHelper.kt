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



    companion object {
        private const val DATABASE_NAME = "DB"
        private const val DATABASE_VER = 1

        val ID = "_id"
        val TABLE_NAME = " tareas"
        val COLUMN_NOMBRE_TAREA = " nombreTarea"
        val COLUMN_LUGAR = " lugarTarea"
        val COLUMN_USUARIO = " usuarioTarea"
        val COLUMN_FECHA = " fecducidad"
        val COLUMN_DESCRIPCION = " descripcion"
        //var tarea: MutableList<Tareas> = ArrayList()


    }

    override fun onCreate(db: SQLiteDatabase?) {
      /*  val createTable =
            "CREATE TABLE " + Table.items.TABLE_NAME + "( " +
                    Table.items.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Table.items.COLUMN_NOMBRE_TAREA + " TEXT, " +
                    Table.items.COLUMN_LUGAR + " TEXT, " +
                    Table.items.COLUMN_USUARIO + " TEXT, " +
                    Table.items.COLUMN_FECHA + " TEXT, " +
                    Table.items.COLUMN_DESCRIPCION + "TEXT ); "

        db?.execSQL(createTable)*/
       // db?.delete(Table.items.TABLE_NAME, null, null)


        db?.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT , NOMBRE TEXT , LUGAR TEXT,USUARIO TEXT ,FECHA TEXT,DESCRIPCION TEXT)")


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + Table.items.TABLE_NAME)
        onCreate(db)
    }


      fun insertData(nombre:String , lugar:String , usuario:String , fecha:String ,descripcion:String) :Boolean {
           val db = this.writableDatabase
           val values = ContentValues()

               values.put(COLUMN_NOMBRE_TAREA,nombre)
               values.put(COLUMN_LUGAR,lugar)
               values.put(COLUMN_USUARIO, usuario)
               values.put(COLUMN_FECHA,fecha)
               values.put(COLUMN_DESCRIPCION,descripcion)


           var result = db.insert(Table.items.TABLE_NAME, null, values)
          return !result.equals(-1)
       }

    /* fun getData(): MutableList<Tareas> {

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
     }*/

    fun getDelete(nombreTarea: String) {
        val db = this.writableDatabase
        db.delete("DELETE FROM" + Table.items.TABLE_NAME, "WHERE nombreTarea='$nombreTarea'", null)
        db.close()

    }
}


