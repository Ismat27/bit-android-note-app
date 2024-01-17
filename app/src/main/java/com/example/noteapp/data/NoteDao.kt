package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("select * from notes")
    fun getNotes(): Flow<List<Note>>

    @Query("select * from notes where id = :noteId")
    fun getNoteById(noteId: Int): Flow<Note>

    @Insert
    suspend fun create(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun remove(note: Note)

}