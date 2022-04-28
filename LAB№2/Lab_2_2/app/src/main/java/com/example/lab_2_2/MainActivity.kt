package com.example.lab_2_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lab_2_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val words: Array<String> = arrayOf("orange", "cheese", "contract", "conversation", "chicken", "basketball", "paradox", "beautiful", "pressure", "improvement")

    private fun shuffle(string: String): String {
        val word: CharArray = string.toCharArray()
        word.shuffle()
        return String(word)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var word: String = words.random()
        binding.word.text = shuffle(word)


        binding.button.setOnClickListener{
            if (binding.answer.text.toString() == word) {
                Toast.makeText(applicationContext,"Right!", Toast.LENGTH_SHORT).show()
                word = words.random()
                binding.word.text = shuffle(word)
                binding.answer.setText("")
            } else {
                Toast.makeText(applicationContext,"Wrong!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}