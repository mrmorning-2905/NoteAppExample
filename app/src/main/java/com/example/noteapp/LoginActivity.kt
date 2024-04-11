package com.example.noteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ActivityLoginBinding
import com.example.noteapp.fragment.LoginFragment
import com.example.noteapp.utils.FragmentUtils

class LoginActivity: AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            FragmentUtils.replaceFragment(this, LoginFragment())
        }
    }
}