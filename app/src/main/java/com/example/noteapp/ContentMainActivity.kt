package com.example.noteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ContentMainActivityBinding
import com.example.noteapp.fragment.AllNoteFragment
import com.example.noteapp.utils.FragmentUtils

class ContentMainActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) { ContentMainActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            FragmentUtils.replaceFragment(this, AllNoteFragment())
        }
    }
}