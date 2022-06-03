package com.example.lab_7

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class CameraButtonReceiver:  BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        //Toast.makeText(context, "Camera button click", Toast.LENGTH_SHORT).show()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                "CAMERA_BUTTON",
                "camera",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Camera button has been clicked"

            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context!!, "CAMERA_BUTTON")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("CAMERA BUTTON")
            .setContentText("You have clicked on the camera button. Congratulation!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(context).apply {
            this.notify(1, builder.build())
        }

        val dialog = ReceiverDialog("CAMERA BUTTON","You have clicked on the camera button. Congratulation!")
        dialog.show((context as MainActivity).supportFragmentManager, "camera")
    }
}