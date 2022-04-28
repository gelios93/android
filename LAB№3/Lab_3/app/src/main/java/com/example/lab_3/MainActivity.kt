package com.example.lab_3

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.lab_3.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

private const val FILE_NAME = "image.jpg"
private const val TAKE_REQUEST_CODE = 1
private const val SEND_REQUEST_CODE = 77

class MainActivity : AppCompatActivity() {

    private lateinit var fileProvider: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            fileProvider = FileProvider.getUriForFile(this, "com.example.fileprovider", getPhotoFile(FILE_NAME))
            
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

            startActivityForResult(takePictureIntent, TAKE_REQUEST_CODE)
        }

        binding.button2.setOnClickListener {
            if (!this::fileProvider.isInitialized) {
                Toast.makeText(applicationContext, "There is no photo", Toast.LENGTH_SHORT).show()
            } else {
                val sendPhotoIntent = Intent(Intent.ACTION_SEND)
                sendPhotoIntent.putExtra(Intent.EXTRA_EMAIL,  arrayOf("hodovychenko.labs@gmail.com"))
                sendPhotoIntent.putExtra(Intent.EXTRA_SUBJECT, "УИ-191 Деревенча Владимир")
                sendPhotoIntent.putExtra(Intent.EXTRA_STREAM, fileProvider)
                sendPhotoIntent.type = "image/jpg"

                startActivityForResult(Intent.createChooser(sendPhotoIntent, "Send photo"), SEND_REQUEST_CODE)
            }

        }

    }

    private fun getPhotoFile(fileName: String): File {
        val directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", directory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TAKE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageView.setImageURI(fileProvider)
        } else if (requestCode == SEND_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}