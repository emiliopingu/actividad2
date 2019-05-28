package com.example.actividad2.domain

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import com.example.actividad2.data.ReadTask
import java.lang.Long.getLong
import android.widget.Toast
import java.nio.file.Files.delete



class Repository(context: Context) {
    val dbHelper = TaskHelper(context)

    val db = dbHelper.writableDatabase

    val itemIds = mutableListOf<Long>()


    fun insertTask(Task_name: String , place : String,user:String,date:String,description:String) {

        val values = ContentValues().apply {
            put(ReadTask.Entry.COLUMN_TASK_NAME, Task_name)
            put(ReadTask.Entry.COLUMN_PLACE, place)
            put(ReadTask.Entry.COLUMN_USER, user)
            put(ReadTask.Entry.COLUMN_DATE, date)
            put(ReadTask.Entry.COLUMN_DESCRIPTION, description)
        }
        val newTask = db?.insert(ReadTask.Entry.TABLE_NAME, null, values)
        db.close()

        if (!newTask?.equals(-1)!!) {
            Log.i("log1","funciona")
        }else{
            Log.i("log12"," No funciona")
        }
    }



    fun deleteTask(task_name: String){
        val selection = "${ReadTask.Entry.COLUMN_TASK_NAME} LIKE ?"
        val deletedRows = db.delete(ReadTask.Entry.TABLE_NAME, selection, arrayOf(task_name))

    }
    fun ReadCusor() {
        val projection = arrayOf(
            BaseColumns._ID, ReadTask.Entry.COLUMN_TASK_NAME, ReadTask.Entry.COLUMN_PLACE,
            ReadTask.Entry.COLUMN_USER, ReadTask.Entry.COLUMN_DATE, ReadTask.Entry.COLUMN_DESCRIPTION
        )


        val selection = "${ReadTask.Entry.COLUMN_TASK_NAME} = ?"
        val selectionArgs = arrayOf("My task")

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${ReadTask.Entry.COLUMN_DESCRIPTION} DESC"

        val cursor = db.query(
            ReadTask.Entry.COLUMN_TASK_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order

        )
        val itemIds = mutableListOf<Long>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                itemIds.add(itemId)
            }
        }
    }


}