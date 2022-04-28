package com.example.lab_4

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FriendRecycleAdapter (
    private val inflater: LayoutInflater, private val context: Context, var listener: OnItemClickListener?) :
    ListAdapter<MainActivity.Friend, FriendRecycleAdapter.ViewHolder>(FriendDiffCallback()) {


    interface OnItemClickListener{
        fun onDeleteClick(position: Int)
    }

    inner class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {


        private val name = view.findViewById<TextView>(R.id.name)
        private val image = view.findViewById<ImageView>(R.id.image)
        private val deleteButton = view.findViewById<ImageButton>(R.id.deleteButton)
        private val sendButton = view.findViewById<ImageButton>(R.id.sendButton)

        fun bind(friend: MainActivity.Friend) {
            name.text = friend.name
            image.setImageResource(context.resources.getIdentifier("com.example.lab_4:drawable/" + "sticker_" + (1..5).random(), null, null))
            deleteButton.setOnClickListener {
                listener?.onDeleteClick(currentList.indexOf(friend))
            }
            sendButton.setOnClickListener {
                val intent = Intent(context, MessageActivity::class.java)
                startActivity(context, intent, null)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.friend_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = getItem(position)
        holder.bind(friend)

    }

    class FriendDiffCallback : DiffUtil.ItemCallback<MainActivity.Friend>() {

        override fun areItemsTheSame(
            oldItem: MainActivity.Friend,
            newItem: MainActivity.Friend
        ): Boolean  = oldItem == newItem


        override fun areContentsTheSame(
            oldItem: MainActivity.Friend,
            newItem: MainActivity.Friend
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }

}