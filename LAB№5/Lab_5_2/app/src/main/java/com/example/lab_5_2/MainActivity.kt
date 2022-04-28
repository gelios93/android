package com.example.lab_5_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lab_5_2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    var arrayList: ArrayList<Fragment> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arrayList.add(FirstFragment.newInstance(0))
        arrayList.add(SecondFragment.newInstance(1))
        arrayList.add(ThirdFragment.newInstance(2))

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.adapter = MyAdapter(this)

        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, pos ->
            tab.text = "TAB ${pos+1}"
        }.attach()
    }

    inner class MyAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount() = 3
        override fun createFragment(position: Int) = arrayList.get(position)
    }
}