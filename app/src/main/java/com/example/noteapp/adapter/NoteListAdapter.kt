package com.example.noteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Notes
import com.example.noteapp.databinding.NoteItemBinding

class NoteListAdapter : ListAdapter<Notes, NoteListAdapter.VH>(NoteItemCallback) {

    private var itemClickListener: ItemClickListener? = null

    fun setItemClickListener(clickListener: ItemClickListener) {
        itemClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH.from(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position).let {noteItem ->
            holder.bind(noteItem)
            holder.itemView.setOnClickListener {
                itemClickListener?.onClickItem(noteItem)
            }
        }
    }

    class VH private constructor(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notes: Notes) {
            binding.noteTitle.text = notes.title
            binding.noteDescription.text = notes.description
        }

        companion object {
            fun from(parent: ViewGroup): VH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NoteItemBinding.inflate(layoutInflater, parent, false)
                return VH(binding)
            }
        }
    }

}

object NoteItemCallback: DiffUtil.ItemCallback<Notes>() {
    override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
        return oldItem.title == newItem.title && oldItem.description == newItem.description
    }

    override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
        return oldItem == newItem
    }

}


