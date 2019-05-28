package com.example.actividad2.domain

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.actividad2.data.ReadTask

class TaskHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.delete(ReadTask.Entry.TABLE_NAME,null,null)
        onCreate(db)

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(queryCreate)
    }

    companion object {

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "TaskReader.BD"
        private const val queryCreate =
            " CREATE TABLE ${ReadTask.Entry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${ReadTask.Entry.COLUMN_TASK_NAME} TEXT," +
                    "${ReadTask.Entry.COLUMN_PLACE} TEXT," +
                    "${ReadTask.Entry.COLUMN_USER} TEXT," +
                    "${ReadTask.Entry.COLUMN_DATE} TEXT," +
                    "${ReadTask.Entry.COLUMN_DESCRIPTION} TEXT)"
    }


}