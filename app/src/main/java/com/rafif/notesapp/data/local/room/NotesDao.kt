package com.rafif.notesapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rafif.notesapp.data.local.entity.Notes


@Dao
interface NotesDao {
    @Insert
    suspend fun addNote(note: Notes)

    @Query("SELECT * FROM tb_notes ORDER BY id ASC")
    fun getAllNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM tb_notes WHERE title LIKE :query")
    fun searchNotesByQuery(query: String) : LiveData<List<Notes>>

    @Query("SELECT * FROM tb_notes ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority() : LiveData<List<Notes>>

    @Query("SELECT * FROM tb_notes ORDER BY CASE WHEN priority LIKE 'H%' THEN 3 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 1 END")
    fun sortByLowPriority() : LiveData<List<Notes>>

    @Query("DELETE FROM tb_notes")
    suspend fun deleteAllData()

    @Delete
    suspend fun deleteNote(notes: Notes)
}