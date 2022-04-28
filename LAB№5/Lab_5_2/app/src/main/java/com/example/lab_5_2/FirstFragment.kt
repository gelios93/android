package com.example.lab_5_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab_5_2.databinding.FragmentFirstBinding
import com.example.lab_5_2.databinding.FragmentSecondBinding

class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textView.text = "Fragment ${arguments?.getInt("position",-1)}"
    }

    companion object {
        fun newInstance(position: Int): FirstFragment {
            val args = Bundle()
            args.putInt("position", position+1)

            val fragment = FirstFragment()
            fragment.arguments = args
            return fragment
        }
    }
}