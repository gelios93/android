package com.example.lab_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab_4.databinding.ActivityMainBinding
import java.sql.DriverManager.println

class MainActivity : AppCompatActivity() {

    private val list: MutableList<Friend> = mutableListOf()
    private val names: Array<String> = arrayOf("Rohan", "Alex", "Nate", "Connor", "Frank", "Luisa", "Adele", "Opal", "Korra", "Mako")


    override fun onCreate(savedInstanceState: Bundle?) {
        list.add(Friend("Vova"))
        list.add(Friend("Yana"))
        list.add(Friend("Veronika"))
        list.add(Friend("Archi"))
        list.add(Friend("Ighor"))
        println(list)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)


//        val binding = ActivityMainRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = FriendRecycleAdapter(layoutInflater, this, null)


        adapter.listener = object: FriendRecycleAdapter.OnItemClickListener {
            override fun onDeleteClick(position: Int) {
                list.removeAt(position)
                adapter.notifyItemRemoved(position)
                //adapter.submitList(list)

            }
        }

        binding.list.adapter = adapter
        binding.list.setHasFixedSize(true)
        binding.list.layoutManager = LinearLayoutManager(this)
        adapter.submitList(list)
        
        binding.buttonAdd.setOnClickListener {
            list.add(Friend(names.random()))
            adapter.notifyItemInserted(list.size)
        }



//        binding.btnAdd.setOnClickListener {
//            val user = Friend(faker.name().fullName())
//            list.add(user)
//
//            adapter.submitList(list.toList())
//        }
//
//        binding.btnRemove.setOnClickListener {
//            if (list.isNotEmpty())
//                list.removeLast()
//
//            adapter.submitList(list.toList())
//        }
//
//        binding.btnClear.setOnClickListener {
//            list.clear()
//            adapter.submitList(list.toList())
//        }
    }

    data class Friend(val name: String)
}