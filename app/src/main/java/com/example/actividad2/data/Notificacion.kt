package com.example.actividad2.data

import android.R
import android.support.v4.app.NotificationCompat
import android.content.Context.NOTIFICATION_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.annotation.TargetApi
import android.content.Context
import android.content.ContextWrapper


class Notificacion(base: Context, val nombre:String) : ContextWrapper(base) {

    private var mManager: NotificationManager? = null

    val manager: NotificationManager?
        get() {
            if (mManager == null) {
                mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }

            return mManager
        }

    val channelNotification: NotificationCompat.Builder
        get() = NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle("Tarea")
            .setContentText("La tarea " +nombre+ "está por acabar")
            .setSmallIcon(R.drawable.ic_dialog_alert)

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)

        manager!!.createNotificationChannel(channel)
    }

    companion object {
        val channelID = "channelID"
        val channelName = "Channel Name"
    }
}