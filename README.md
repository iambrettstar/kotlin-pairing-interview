# Secure Notes API

A simple Kotlin + Ktor REST API for storing secure notes per user.

## Features

- Create, retrieve, and delete personal notes
- In-memory storage (per user)
- Simple API key authentication

## API Keys

| API Key | Username |
|--------|----------|
| `abc123` | `alice` |
| `def456` | `bob`   |

Use the `X-API-Key` header in your requests.

## Running Locally

### With Gradle

```bash
./gradlew run
```

### With Docker

```bash
docker build -t secure-notes-api .
docker run -p 8080:8080 secure-notes-api
```

## Sample Requests

### Create a note
```bash
curl -X POST http://localhost:8080/notes \
  -H "Content-Type: application/json" \
  -H "X-API-Key: abc123" \
  -d '{ "title": "Note 1", "content": "Hello World", "tags": ["demo"] }'
```

### Get notes
```bash
curl -H "X-API-Key: abc123" http://localhost:8080/notes
```
