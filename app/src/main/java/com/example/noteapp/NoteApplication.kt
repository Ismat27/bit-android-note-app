package com.example.noteapp

import android.app.Application
import com.example.noteapp.data.AppContainer
import com.example.noteapp.data.AppDataContainer

class NoteApplication: Application() {

    private lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }

}