package com.example.noteapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteEntryBinding

class NoteEntryFragment : Fragment(R.layout.fragment_note_entry) {

    private lateinit var binding: FragmentNoteEntryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNoteEntryBinding.inflate(inflater, container, false)
        return binding.root
    }
}