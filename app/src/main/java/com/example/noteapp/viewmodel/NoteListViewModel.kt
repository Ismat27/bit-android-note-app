package com.example.noteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.noteapp.NoteApplication
import com.example.noteapp.data.Note
import com.example.noteapp.data.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteListViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val notesStream: Flow<List<Note>> = noteRepository.notesStream


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NoteApplication)
                NoteListViewModel(application.container.noteRepository)
            }
        }
    }


}