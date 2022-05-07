package com.example.lab_6

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lab_6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val list: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (i in 40 downTo 1 step 1) {
            list.add(Item((1..99).random(), Color.argb(200, (0..255).random(), (0..255).random(), (0..255).random()) ))
        }

        val adapter = ItemAdapter(layoutInflater, supportFragmentManager)

        //Так как в задании размер (а именно ширина) элементов фиксированный, нужно определить
        //Сколько полных столбцов размерностью 96dp + отступы по 4dp влезет в экран
        val x = ((resources.displayMetrics.widthPixels / resources.displayMetrics.density)/104).toInt()
        binding.list.layoutManager = GridLayoutManager(this, x)

        binding.list.setHasFixedSize(true)
        binding.list.adapter = adapter
        adapter.submitList(list)

    }


    data class Item(val value: Int, val background: Int)
}