package com.example.noteapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.noteapp.databinding.AppInfoFragmentBinding

class AppInfoFragment : Fragment() {
    private lateinit var binding: AppInfoFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AppInfoFragmentBinding.inflate(inflater, container, false)
        setupToolbar()
        return binding.root
    }

    private fun setupToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }
}