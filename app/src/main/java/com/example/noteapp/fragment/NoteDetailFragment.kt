package com.example.noteapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Lifecycle
import com.example.noteapp.R
import com.example.noteapp.databinding.NoteDetailFragmentBinding
import com.example.noteapp.utils.FragmentUtils
import com.example.noteapp.utils.KEY_DELETE_NOTE_REQUEST
import com.example.noteapp.utils.KEY_EDIT_NOTE_REQUEST
import com.example.noteapp.utils.KEY_NOTE_DESCRIPTION
import com.example.noteapp.utils.KEY_NOTE_ID
import com.example.noteapp.utils.KEY_NOTE_LAST_MODIFIED
import com.example.noteapp.utils.KEY_NOTE_TITLE
import com.example.noteapp.utils.formatTime

class NoteDetailFragment : Fragment() {

    private lateinit var binding: NoteDetailFragmentBinding
    private val currentNoteId: Int
        get() = arguments?.getInt(KEY_NOTE_ID) ?: -1

    private val currentNoteTitle: String
        get() = arguments?.getString(KEY_NOTE_TITLE) ?: ""

    private val currentNoteDescription: String
        get() = arguments?.getString(KEY_NOTE_DESCRIPTION) ?: ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoteDetailFragmentBinding.inflate(inflater, container, false)
        setupToolbar()
        setupMenu()
        setUpFab()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setupToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            title = ""
            setHomeAsUpIndicator(R.drawable.back)
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)

        }
    }

    private fun setupMenu() {
        val menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.detail_note_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.delete_menu -> {
                        //todo delete note
                        deleteNote()
                        Toast.makeText(context, "Deleted this note!", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.share_menu -> {
                        // todo show chooser activity
                        Toast.makeText(context, "Share this note!", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
        }

        requireActivity().addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun deleteNote() {
        val bundle = Bundle().apply {
            putInt(KEY_NOTE_ID, currentNoteId)
        }
        setFragmentResult(KEY_DELETE_NOTE_REQUEST, bundle)
        FragmentUtils.popBackStack(requireActivity())
    }
    private fun setUpView() {
        val noteLastModified = arguments?.getLong(KEY_NOTE_LAST_MODIFIED) ?: 0L
        binding.noteTitle.setText(currentNoteTitle, TextView.BufferType.EDITABLE)
        binding.noteLastModified.text = formatTime(noteLastModified)
        binding.noteDescription.setText(currentNoteDescription, TextView.BufferType.EDITABLE)
    }

    private fun setUpFab() {
        binding.saveFabBtn.setOnClickListener {
            //todo save note
            val editedNote = saveNote()
            var message: String = ""
            if (editedNote) {
                message = "Edited this note"
                FragmentUtils.popBackStack(requireActivity())
            } else {
                message = "Please edit this note before click"
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveNote(): Boolean {
        val newTitle = binding.noteTitle.text.toString()
        val newDescription = binding.noteDescription.text.toString()
        if ((newTitle.isNotEmpty() && newTitle != currentNoteTitle)
            || (newDescription.isNotEmpty() && newDescription != currentNoteDescription)) {
            val bundle = Bundle().apply {
                putInt(KEY_NOTE_ID, currentNoteId)
                putString(KEY_NOTE_TITLE, newTitle)
                putString(KEY_NOTE_DESCRIPTION, newDescription)
                putLong(KEY_NOTE_LAST_MODIFIED, System.currentTimeMillis())
            }
            setFragmentResult(KEY_EDIT_NOTE_REQUEST, bundle)
            return true
        }
        return false
    }
}