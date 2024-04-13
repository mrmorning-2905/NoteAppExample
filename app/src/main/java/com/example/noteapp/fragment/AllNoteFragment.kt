package com.example.noteapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.NOTE_LIST
import com.example.noteapp.Notes
import com.example.noteapp.R
import com.example.noteapp.adapter.ItemClickListener
import com.example.noteapp.adapter.NoteListAdapter
import com.example.noteapp.databinding.AllNoteFragmentBinding
import com.example.noteapp.utils.FragmentUtils
import com.example.noteapp.utils.KEY_ADD_NOTE_REQUEST
import com.example.noteapp.utils.KEY_DELETE_NOTE_REQUEST
import com.example.noteapp.utils.KEY_EDIT_NOTE_REQUEST
import com.example.noteapp.utils.KEY_NOTE_DESCRIPTION
import com.example.noteapp.utils.KEY_NOTE_ID
import com.example.noteapp.utils.KEY_NOTE_LAST_MODIFIED
import com.example.noteapp.utils.KEY_NOTE_TITLE

class AllNoteFragment : Fragment() {

    private lateinit var binding: AllNoteFragmentBinding
    private val noteListAdapter by lazy(LazyThreadSafetyMode.NONE) { NoteListAdapter() }
    private val requestArr = arrayOf(KEY_ADD_NOTE_REQUEST, KEY_DELETE_NOTE_REQUEST, KEY_EDIT_NOTE_REQUEST)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initResultRequestListener()
    }

    private fun initResultRequestListener() {
        for (request in requestArr) {
            listenFragmentResult(request)
        }
    }

    private fun listenFragmentResult(requestType: String) {
        setFragmentResultListener(requestType) { _, bundle ->
            when (requestType) {
                KEY_DELETE_NOTE_REQUEST -> {
                    val deletedId = bundle.getInt(KEY_NOTE_ID)
                    Log.d("sangpd", "onCreate_deletedId: $deletedId")
                    if (deletedId != -1) {
                        noteListAdapter.submitList(
                            noteListAdapter.currentList.filter { it.id != deletedId }
                        )
                    }
                }
                KEY_EDIT_NOTE_REQUEST -> {
                    val editNoteId = bundle.getInt(KEY_NOTE_ID)
                    if (editNoteId != -1) {
                        val newTitle = bundle.getString(KEY_NOTE_TITLE) ?: ""
                        val newDescription = bundle.getString(KEY_NOTE_DESCRIPTION) ?: ""
                        val lastModified = bundle.getLong(KEY_NOTE_LAST_MODIFIED)
                        noteListAdapter.submitList(
                            noteListAdapter.currentList.map { noteItem ->
                                if (noteItem.id == editNoteId) noteItem.copy(title = newTitle, description = newDescription, lastModify = lastModified)
                                else noteItem
                            }
                        )
                    }
                }
                KEY_ADD_NOTE_REQUEST -> {
                    val newNoteId = bundle.getInt(KEY_NOTE_ID)
                    val newTitle = bundle.getString(KEY_NOTE_TITLE) ?: ""
                    val newDescription = bundle.getString(KEY_NOTE_DESCRIPTION) ?: ""
                    val lastModified = bundle.getLong(KEY_NOTE_LAST_MODIFIED)
                    noteListAdapter.submitList(
                        listOf(Notes(newNoteId, newTitle, newDescription, lastModified)) + noteListAdapter.currentList
                    )
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AllNoteFragmentBinding.inflate(inflater, container, false)
        setupToolbar()
        setupMenu()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupFab()
    }

    private fun setupToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            title = "All Notes"
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)
        }
    }

    private fun setupFab() {
        binding.addMoreNote.setOnClickListener {
            FragmentUtils.replaceFragment(requireActivity(), AddNoteFragment(), true)
        }
    }

    private fun setupMenu() {
        val menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.all_note_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.app_info -> {
                        FragmentUtils.replaceFragment(requireActivity(), AppInfoFragment(), true)
                        true
                    }
                    else -> false
                }
            }
        }
        requireActivity().addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupView() {
        binding.noteRecycleView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter.apply {
                submitList(NOTE_LIST)
                setItemClickListener(onItemClickListener)
            }
        }

        binding.emptyNoteLayout.visibility =
            if (noteListAdapter.itemCount == 0) View.VISIBLE else View.GONE
    }

    private val onItemClickListener = object : ItemClickListener {
        override fun onClickItem(note: Notes) {
            openDetailNoteScreen(note)
        }
    }

    private fun openDetailNoteScreen(note: Notes) {
        val bundle = Bundle().apply {
            putInt(KEY_NOTE_ID, note.id)
            putString(KEY_NOTE_TITLE, note.title)
            putString(KEY_NOTE_DESCRIPTION, note.description)
            putLong(KEY_NOTE_LAST_MODIFIED, note.lastModify)
        }

        val detailFragment = NoteDetailFragment().apply {
            arguments = bundle
        }

        FragmentUtils.replaceFragment(requireActivity(), detailFragment, true)
    }
}