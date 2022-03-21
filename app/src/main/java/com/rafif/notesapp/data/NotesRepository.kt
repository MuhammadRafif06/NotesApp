package com.rafif.notesapp.data

import com.rafif.notesapp.data.local.entity.Notes
import androidx.lifecycle.LiveData
import com.rafif.notesapp.data.local.room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    val getAllNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insertNotes(notes: Notes) {
        notesDao.addNote(notes)
    }
}