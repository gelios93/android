package com.example.lab_6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class ItemAdapter (private val inflater: LayoutInflater, private val manager: FragmentManager) : ListAdapter<MainActivity.Item, ItemAdapter.ViewHolder>(ItemDiffCallback()) {

    inner class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private val card = view.findViewById<MaterialCardView>(R.id.card)
        private val value = view.findViewById<TextView>(R.id.value)

        fun bind(item: MainActivity.Item) {
            card.setCardBackgroundColor(item.background)
            value.setText(item.value.toString())
            card.setOnClickListener {
                val dialog = ItemDialog(item.value, item.background)
                dialog.show(manager, "dlg")
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = getItem(position)
        holder.bind(friend)

    }

    class ItemDiffCallback : DiffUtil.ItemCallback<MainActivity.Item>() {

        override fun areItemsTheSame(
            oldItem: MainActivity.Item,
            newItem: MainActivity.Item
        ): Boolean  = oldItem == newItem


        override fun areContentsTheSame(
            oldItem: MainActivity.Item,
            newItem: MainActivity.Item
        ): Boolean {
            return oldItem.value == newItem.value && oldItem.background.equals(newItem.background)
        }
    }
}