package com.example.noteapp.data

import android.content.Context

class AppDataContainer(private val context: Context) : AppContainer {

    override val noteRepository: NoteRepository by lazy {
        RoomNoteRepository(AppDatabase.getDatabase(context).getNoteDao())
    }


}