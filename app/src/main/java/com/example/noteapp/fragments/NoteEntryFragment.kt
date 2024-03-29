package com.example.noteapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteEntryBinding
import com.example.noteapp.viewmodel.NoteEntryViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class NoteEntryFragment : Fragment(R.layout.fragment_note_entry) {

    private lateinit var binding: FragmentNoteEntryBinding
    private val viewModel by viewModels<NoteEntryViewModel> { NoteEntryViewModel.Factory }

    private val args: NoteEntryFragmentArgs by navArgs()

    private val promptModal =
        PromptModalFragment("Add new note", "This will create a new note for you.\nContinue?") {
            submitNote()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNoteEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteId = args.noteId

        binding.apply {
            btnPreviousNotes.setOnClickListener {
                findNavController().popBackStack()
            }

            btnSave.setOnClickListener {
                if (validateInput()) {
                    promptModal.show(childFragmentManager, "save or update modal")
                } else {
                    Toast.makeText(requireContext(), "Enter note title and body", Toast.LENGTH_LONG)
                        .show()
                }
            }

        }

        if (noteId > 0) {

            promptModal.title = "Edit note"
            promptModal.body = "This will update the content of your note.\nContinue?."

            with(binding) {
                btnAddNote.text = getString(R.string.edit_note)
                newNote.text = getString(R.string.edit_note)
                btnSave.text = getString(R.string.update_note)
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.getNoteStream(noteId).filterNotNull().collect {
                        with(binding) {
                            etNoteTitle.setText(it.title)
                            etNoteBody.setText(it.body)
                        }
                    }
                }
            }
        }

    }

    private fun submitNote() {
        val title = binding.etNoteTitle.text.toString().trim()
        val body = binding.etNoteBody.text.toString().trim()
        viewModel.saveOrUpdateNote(args.noteId, title, body)
        if (args.noteId == 0) {
            binding.etNoteTitle.setText("")
            binding.etNoteBody.setText("")
        }
        promptModal.dismiss()
    }

    private fun validateInput(): Boolean {
        if (binding.etNoteTitle.text.toString().isBlank()) {
            return false
        }
        return binding.etNoteBody.text.toString().isNotBlank()
    }
}