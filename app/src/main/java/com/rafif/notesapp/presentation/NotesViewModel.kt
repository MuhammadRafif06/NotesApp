package com.rafif.notesapp.presentation

import com.rafif.notesapp.data.local.entity.Notes
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rafif.notesapp.data.NotesRepository
import com.rafif.notesapp.data.local.room.NotesDao
import com.rafif.notesapp.data.local.room.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val notesDao : NotesDao = NotesDatabase.getDatabase(application).notesDao()
    private val notesRepository = NotesRepository(notesDao)

    fun insertNotes(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNotes(note)
        }
    }
}