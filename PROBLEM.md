# 🧪 Pairing Exercise: Secure Notes API

## 📝 Problem Statement

You are tasked with building a **Secure Notes API**, a backend service that allows users to store and retrieve personal notes. The goal is to implement a RESTful API using **Kotlin** (with **Ktor**) that supports basic CRUD functionality, user scoping, and lightweight authentication.

---

## 🎯 Requirements

### 1. Authentication
- Accept an API key via the `X-API-Key` header.
- Only authenticated users may access their notes.
- Use a simple in-memory map of API keys to usernames.

### 2. Endpoints
- `POST /notes` — Create a new note (`title`, `content`, optional `tags`)
- `GET /notes` — Retrieve all notes for the current user
- `GET /notes/{id}` — Retrieve a specific note (only if owned by the user)
- `DELETE /notes/{id}` — Delete a note (only if owned by the user)

### 3. Data Model
Each note should contain:
- A unique ID (UUID)
- Title (String)
- Content (String)
- Tags (List of Strings)
- Owner (String, based on API key)

### 4. Security
- Users must only access their own notes.
- Ensure the API does not leak data or metadata from other users.

### 5. In-Memory Storage
Use appropriate data structures to simulate user-based storage (e.g., a map of username → notes).

---

## 🧠 Bonus (If Time Permits)

- Filter notes by tag (`GET /notes?tag=...`)
- Sort by creation or alphabetical order
- Add simple pagination

---

## ⏱ Time

You’ll be working on this live during the pairing session. You don't need to finish everything — the goal is to demonstrate how you approach:

- Problem-solving
- Writing clean and idiomatic Kotlin
- Communication and collaboration
- Making tradeoffs (security, performance, data structure choice)
