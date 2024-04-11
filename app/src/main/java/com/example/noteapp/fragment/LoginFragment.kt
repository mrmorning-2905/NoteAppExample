package com.example.noteapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteapp.ContentMainActivity
import com.example.noteapp.databinding.LoginFragmentBinding
import com.example.noteapp.utils.FragmentUtils

class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.signUpBtn.setOnClickListener {
            FragmentUtils.replaceFragment(requireActivity(), CreateAccountFragment(), true)
        }
        binding.loginBtn.setOnClickListener {
            val intent = Intent(requireContext(), ContentMainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}