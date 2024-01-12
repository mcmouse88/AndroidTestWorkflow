package com.mcmouse88.hrenapplication

import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.minutes

interface NoteRepository {
    suspend fun getPost(): List<Note>
    suspend fun longOperation()
}

data class Note(
    val title: String,
    val description: String
)

@Singleton
class NoteRepositoryImpl @Inject constructor() : NoteRepository {

    override suspend fun getPost(): List<Note> {
        return emptyList()
    }

    override suspend fun longOperation() {
        delay(5.minutes)
    }
}

