package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import kotlinx.serialization.*
import java.util.*

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::secureNotesModule).start(wait = true)
}

fun Application.secureNotesModule() {
    install(ContentNegotiation) {
        json()
    }

    val noteService = NoteService()

    routing {
        route("/notes") {
            post {
                val apiKey = call.request.headers["X-API-Key"]
                val user = InMemoryAuth.authenticate(apiKey)
                if (user == null) {
                    call.respond(HttpStatusCode.Unauthorized)
                    return@post
                }

                val request = call.receive<CreateNoteRequest>()
                val note = noteService.createNote(user, request)
                call.respond(note)
            }

            get {
                val apiKey = call.request.headers["X-API-Key"]
                val user = InMemoryAuth.authenticate(apiKey)
                if (user == null) {
                    call.respond(HttpStatusCode.Unauthorized)
                    return@get
                }

                val notes = noteService.getNotesForUser(user)
                call.respond(notes)
            }

            get("{id}") {
                val apiKey = call.request.headers["X-API-Key"]
                val user = InMemoryAuth.authenticate(apiKey)
                if (user == null) {
                    call.respond(HttpStatusCode.Unauthorized)
                    return@get
                }

                val id = call.parameters["id"]?.let { UUID.fromString(it) }
                val note = id?.let { noteService.getNoteForUser(user, it) }
                if (note == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    call.respond(note)
                }
            }

            delete("{id}") {
                val apiKey = call.request.headers["X-API-Key"]
                val user = InMemoryAuth.authenticate(apiKey)
                if (user == null) {
                    call.respond(HttpStatusCode.Unauthorized)
                    return@delete
                }

                val id = call.parameters["id"]?.let { UUID.fromString(it) }
                val deleted = id?.let { noteService.deleteNoteForUser(user, it) } ?: false
                if (deleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}