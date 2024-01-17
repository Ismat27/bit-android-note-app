package com.example.noteapp.data

import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    val notesStream: Flow<List<Note>>

    fun getNoteStreamById(noteId: Int): Flow<Note>

    suspend fun createNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun removeNote(note: Note)

}