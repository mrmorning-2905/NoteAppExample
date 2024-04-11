package com.example.noteapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteapp.databinding.CreateAccountFragmentBinding

class CreateAccountFragment : Fragment() {
    private lateinit var binding: CreateAccountFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CreateAccountFragmentBinding.inflate(inflater, container, false)
        binding.backNavigationBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.signInBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}