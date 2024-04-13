package com.example.noteapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteapp.databinding.CreateAccountFragmentBinding
import com.example.noteapp.utils.FragmentUtils

class CreateAccountFragment : Fragment() {
    private lateinit var binding: CreateAccountFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CreateAccountFragmentBinding.inflate(inflater, container, false)
        binding.backNavigationBtn.setOnClickListener {
            FragmentUtils.popBackStack(requireActivity())
        }
        binding.signInBtn.setOnClickListener {
            FragmentUtils.popBackStack(requireActivity())
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}