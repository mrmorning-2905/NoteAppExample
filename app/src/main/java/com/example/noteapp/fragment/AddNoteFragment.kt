package com.example.noteapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.noteapp.R
import com.example.noteapp.databinding.AddNewNoteFragmentBinding
import com.example.noteapp.utils.FragmentUtils
import com.example.noteapp.utils.KEY_ADD_NOTE_REQUEST
import com.example.noteapp.utils.KEY_EDIT_NOTE_REQUEST
import com.example.noteapp.utils.KEY_NOTE_DESCRIPTION
import com.example.noteapp.utils.KEY_NOTE_ID
import com.example.noteapp.utils.KEY_NOTE_LAST_MODIFIED
import com.example.noteapp.utils.KEY_NOTE_TITLE
import kotlin.random.Random

class AddNoteFragment : Fragment() {

    private lateinit var binding: AddNewNoteFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddNewNoteFragmentBinding.inflate(layoutInflater, container, false)
        setupToolbar()
        setUpFab()
        return binding.root
    }

    private fun setupToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Add notes"
            setHomeAsUpIndicator(R.drawable.back)
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    private fun setUpFab() {
        binding.saveFabBtn.setOnClickListener {
            //todo save note

            val executeAddNote = addNewNote()
            var message: String = ""

            if (executeAddNote) {
                message = "A new note has been added!"
                FragmentUtils.popBackStack(requireActivity())
            } else {
                message = "Please add your note title and description"
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun addNewNote(): Boolean {
        val newTitle = binding.addNoteTitle.text.toString()
        val newDescription = binding.addNoteDescription.text.toString()
        if (newTitle.isNotEmpty() && newDescription.isNotEmpty()) {
            val bundle = Bundle().apply {
                val newId = Random.nextInt(20, 100)
                putInt(KEY_NOTE_ID, newId)
                putString(KEY_NOTE_TITLE, newTitle)
                putString(KEY_NOTE_DESCRIPTION, newDescription)
                putLong(KEY_NOTE_LAST_MODIFIED, System.currentTimeMillis())
            }
            setFragmentResult(KEY_ADD_NOTE_REQUEST, bundle)
            return true
        }
        return false
    }
}