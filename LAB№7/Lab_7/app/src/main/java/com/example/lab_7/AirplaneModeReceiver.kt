package com.example.lab_7

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AirplaneModeReceiver:  BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        //Toast.makeText(context, "Airplane mode changed", Toast.LENGTH_SHORT).show()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                "AIRPLANE_MODE",
                "Mode",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Airplane mode has been changed"

            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context!!, "AIRPLANE_MODE")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val receiverDialog: ReceiverDialog

        if(Settings.Global.getInt(context?.contentResolver, Settings.Global.AIRPLANE_MODE_ON) == 1) {

            builder
                .setContentTitle("Airplane mode ON")
                .setContentText("Airplane mode is on. Enjoy your loneliness.")

            NotificationManagerCompat.from(context).apply {
                this.notify(2, builder.build())
            }

            receiverDialog = ReceiverDialog("Airplane mode ON","Airplane mode is on. Enjoy your loneliness.")

        }
        else {

            builder
                .setContentTitle("Airplane mode OFF")
                .setContentText("Airplane mode is off. Welcome back.")

            NotificationManagerCompat.from(context).apply {
                this.notify(3, builder.build())
            }

            receiverDialog = ReceiverDialog("Airplane mode OFF","Airplane mode is off. Welcome back.")
        }

        receiverDialog.show((context as MainActivity).supportFragmentManager, "airplane")
    }
}