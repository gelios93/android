package com.example.lab_5_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab_5_2.databinding.FragmentThirdBinding

class ThirdFragment : Fragment(R.layout.fragment_third) {

    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.text = "Fragment ${arguments?.getInt("position",-1)}"
    }

    companion object {
        fun newInstance(position: Int): ThirdFragment {
            val args = Bundle()
            args.putInt("position", position+1);

            val fragment = ThirdFragment()
            fragment.arguments = args
            return fragment
        }
    }
}