package com.rafif.notesapp.data.local.room

import androidx.room.TypeConverter
import com.rafif.notesapp.data.local.entity.Priority

class Converter {
    @TypeConverter
    fun fromPriority(priority: Priority) : String {
        return priority.toString()
    }

    @TypeConverter
    fun toPriority(priority: String) : Priority {
        return Priority.valueOf(priority)
    }
}