package com.example.lab_7

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val lowBatteryReceiver = LowBatteryReceiver()
    private val airplaneModeReceiver = AirplaneModeReceiver()
    private val cameraButtonReceiver = CameraButtonReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        registerReceiver(lowBatteryReceiver, IntentFilter(Intent.ACTION_BATTERY_LOW))

        registerReceiver(airplaneModeReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))

        //Работает ТОЛЬКО с версии 6.0 (Marshmallow) и ниже
        //В последующих версиях подобное можно сделать только с собственными интентами для камеры
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            registerReceiver(cameraButtonReceiver, IntentFilter(Intent.ACTION_CAMERA_BUTTON))
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(lowBatteryReceiver)
        unregisterReceiver(airplaneModeReceiver)
        unregisterReceiver(cameraButtonReceiver)

        println("onStop")
    }

}