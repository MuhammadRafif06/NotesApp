package com.rafif.notesapp.data.local

import android.renderscript.RenderScript

data class Notes (
    val id: Int,
    val title: String,
    val desc: String,
    val date: String,
    val prority: RenderScript.Priority
)