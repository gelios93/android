package com.example.lab_7

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class LowBatteryReceiver:  BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        //Toast.makeText(context, "Battery low", Toast.LENGTH_SHORT).show()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                "LOW_BATTERY",
                "low",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Battery level is too low"

            val notificationManager = context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context!!, "LOW_BATTERY")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("BATTERY LOW")
            .setContentText("Battery level is so low. Please make a charger connection.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(context).apply {
            this.notify(1, builder.build())
        }

        val dialog = ReceiverDialog("BATTERY LOW", "Battery level is so low. Please make a charger connection.")
        dialog.show((context as MainActivity).supportFragmentManager, "battery")
    }
}