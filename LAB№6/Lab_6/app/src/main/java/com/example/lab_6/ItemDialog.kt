package com.example.lab_6

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.alert.view.*

class ItemDialog(private val value: Int, private val background: Int): DialogFragment() {


override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return activity?.let {

        val inflater = requireActivity().layoutInflater;

        val alert = inflater.inflate(R.layout.alert, null)
        alert.label.setBackgroundColor(background)
        alert.number.setText("You have chosen $value")

        AlertDialog.Builder(it).setView(alert)
            .setPositiveButton("Ok", null)
            .create()
    } ?: throw IllegalStateException("Activity cannot be null")
}

}