package com.example

import java.util.*
import kotlinx.serialization.Serializable
import java.util.concurrent.ConcurrentHashMap

class NoteService {
    private val notes = ConcurrentHashMap<String, MutableMap<UUID, Note>>()

    fun createNote(user: String, request: CreateNoteRequest): Note {
        val userNotes = notes.computeIfAbsent(user) { mutableMapOf() }
        val note = Note(UUID.randomUUID(), request.title, request.content, request.tags, user)
        userNotes[note.id] = note
        return note
    }

    fun getNotesForUser(user: String): List<Note> {
        return notes[user]?.values?.toList() ?: emptyList()
    }

    fun getNoteForUser(user: String, id: UUID): Note? {
        return notes[user]?.get(id)
    }

    fun deleteNoteForUser(user: String, id: UUID): Boolean {
        return notes[user]?.remove(id) != null
    }
}

@Serializable
data class CreateNoteRequest(val title: String, val content: String, val tags: List<String> = emptyList())

@Serializable
data class Note(val id: UUID, val title: String, val content: String, val tags: List<String>, val owner: String)