package com.example.noteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.noteapp.NoteApplication
import com.example.noteapp.data.Note
import com.example.noteapp.data.NoteRepository
import kotlinx.coroutines.launch

class NoteEntryViewModel(private val noteRepository: NoteRepository) : ViewModel() {


    fun getNoteStream(noteId: Int) = noteRepository.getNoteStreamById(noteId)

    fun saveOrUpdateNote(id: Int, title: String, body: String) {
        val note = Note(id, title, body)
        viewModelScope.launch {
            if (id > 0) {
                noteRepository.updateNote(note)
            } else {
                noteRepository.createNote(note)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NoteApplication)
                NoteEntryViewModel(application.container.noteRepository)
            }
        }
    }

}