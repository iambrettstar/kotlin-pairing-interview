package com.example

object InMemoryAuth {
    private val users = mapOf(
        "abc123" to "alice",
        "def456" to "bob"
    )

    fun authenticate(apiKey: String?): String? = users[apiKey]
}