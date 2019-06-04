package com.example.actividad2.data

import android.provider.BaseColumns

object ReadTask {
    object Entry : BaseColumns {
        const val TABLE_NAME = "Task"
        const val COLUMN_TASK_NAME = " TaskName"
        const val COLUMN_PLACE = "TaskPlace"
        const val COLUMN_USER = "TaskUser"
        const val COLUMN_DATE = "DateOfExpiry"
        const val COLUMN_DESCRIPTION = "Description"

    }
}