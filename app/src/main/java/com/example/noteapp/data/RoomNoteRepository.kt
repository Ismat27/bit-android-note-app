package com.example.noteapp.data

import kotlinx.coroutines.flow.Flow

class RoomNoteRepository(private val noteDao: NoteDao) : NoteRepository {

    override val notesStream: Flow<List<Note>>
        get() = noteDao.getNotes()

    override fun getNoteStreamById(noteId: Int): Flow<Note> {
        return noteDao.getNoteById(noteId)
    }

    override suspend fun createNote(note: Note) {
        noteDao.create(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.update(note)
    }

    override suspend fun removeNote(note: Note) {
        noteDao.remove(note)
    }
}