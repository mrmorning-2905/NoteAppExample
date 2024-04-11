package com.example.noteapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.NOTE_LIST
import com.example.noteapp.adapter.NoteListAdapter
import com.example.noteapp.databinding.AllNoteFragmentBinding

class AllNoteFragment : Fragment() {

    private lateinit var binding: AllNoteFragmentBinding
    private val noteListAdapter by lazy { NoteListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AllNoteFragmentBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.noteRecycleView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter.apply { submitList(NOTE_LIST) }
        }

        binding.emptyNoteLayout.visibility =
            if (noteListAdapter.itemCount == 0) View.VISIBLE else View.GONE
    }
}