package com.example.lab_5_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab_5_2.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.text = "Fragment ${arguments?.getInt("position",-1)}"
    }

    companion object {
        fun newInstance(position: Int): SecondFragment {
            val args = Bundle()
            args.putInt("position", position+1);

            val fragment = SecondFragment()
            fragment.arguments = args
            return fragment
        }
    }
}