# Kent API - Спецификация

## Base URL

- **Production:** `https://api.kent.app`
- **Development:** `http://localhost:8080`

## Аутентификация

Все защищенные endpoints требуют заголовок:
```
Authorization: Bearer <access_token>
```

## Версионирование

API версионируется через путь: `/api/v1/...`

---

## Endpoints

### Auth

#### POST `/api/v1/auth/send-otp`

Отправка OTP кода.

**Request:**
```json
{
  "phoneNumber": "+1234567890"
}
```

**Response:**
```json
{
  "success": true,
  "expiresIn": 300
}
```

#### POST `/api/v1/auth/verify-otp`

Верификация OTP и получение токенов.

**Request:**
```json
{
  "phoneNumber": "+1234567890",
  "code": "123456"
}
```

**Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": "uuid",
    "phoneNumber": "+1234567890",
    "displayName": null,
    "avatarUrl": null
  }
}
```

---

### Chats

#### GET `/api/v1/chats`

Получить список чатов пользователя.

**Response:**
```json
[
  {
    "id": "uuid",
    "type": "direct",
    "participants": [...],
    "lastMessage": {...},
    "updatedAt": 1234567890
  }
]
```

#### POST `/api/v1/chats/{chatId}/messages`

Отправить сообщение.

**Request:**
```json
{
  "content": "Hello!",
  "type": "text",
  "encrypted": true
}
```

---

### AI

#### POST `/api/v1/ai/chat`

Запрос к AI-ассистенту.

**Request:**
```json
{
  "message": "What is the weather?",
  "context": {
    "chatId": "uuid"
  }
}
```

**Response:**
```json
{
  "response": "The weather is sunny today.",
  "tokensUsed": 150
}
```

---

## Rate Limits

- **Auth endpoints:** 5 requests/minute per IP
- **Chat endpoints:** 100 requests/minute per user
- **AI endpoints:** 20 requests/minute per user (Premium: 100)

---

## WebSocket (Real-time)

### Connection

```
wss://ws.kent.app/v1/stream?token=<access_token>
```

### Events

#### `message.new`
```json
{
  "type": "message.new",
  "data": {
    "id": "uuid",
    "chatId": "uuid",
    "senderId": "uuid",
    "content": "Hello!",
    "type": "text",
    "timestamp": 1234567890,
    "encrypted": true
  }
}
```

