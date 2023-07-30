package com.mcmouse88.hrenapplication

interface NoteRepository {
    suspend fun getPost(): List<Note>
}

data class Note(
    val title: String,
    val description: String
)