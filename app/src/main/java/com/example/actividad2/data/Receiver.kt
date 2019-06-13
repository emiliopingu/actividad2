package com.example.actividad2.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.support.v4.app.NotificationCompat
import android.util.Log


class Receiver(val nombre:String): BroadcastReceiver () {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationHelper = Notificacion(context!!,nombre)
        val nb = notificationHelper.channelNotification
        notificationHelper.manager?.notify(1, nb.build())
        Log.i("Recibido","El receiver funciona")

    }
}