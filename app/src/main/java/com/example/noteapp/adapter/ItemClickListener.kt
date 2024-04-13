package com.example.noteapp.adapter

import com.example.noteapp.Notes

interface ItemClickListener {
    fun onClickItem(note: Notes)
}