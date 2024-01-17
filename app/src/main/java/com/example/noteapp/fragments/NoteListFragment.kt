package com.example.noteapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.NoteListAdapter
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteListBinding
import com.example.noteapp.viewmodel.NoteListViewModel
import kotlinx.coroutines.launch

class NoteListFragment : Fragment(R.layout.fragment_note_list) {

    private lateinit var binding: FragmentNoteListBinding

    private val viewModel by viewModels<NoteListViewModel> { NoteListViewModel.Factory }

    private val adapter = NoteListAdapter(
        onClickCard = { note ->
            findNavController().navigate(
                NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(
                    note.id
                )
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnAddNote.setOnClickListener {
                findNavController().navigate(R.id.action_noteListFragment_to_noteEntryFragment)
            }

            rvNoteList.adapter = adapter
            rvNoteList.layoutManager = LinearLayoutManager(requireContext())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.notesStream.collect {
                    adapter.submitList(it)
                }
            }
        }

    }

}