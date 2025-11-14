# Kent Server

Go backend для мессенджера Kent.

## Быстрый старт

### Docker Compose

```bash
docker-compose up -d
```

### Локально

```bash
go mod download
go run cmd/api/main.go
```

## Переменные окружения

- `DATABASE_URL` - PostgreSQL connection string
- `REDIS_URL` - Redis connection string
- `JWT_SECRET` - Secret для JWT токенов
- `PORT` - Порт сервера (по умолчанию 8080)

## API

- Health: `GET /health`
- Auth: `POST /api/v1/auth/send-otp`, `POST /api/v1/auth/verify-otp`
- Chats: `GET /api/v1/chats`, `POST /api/v1/chats/{chatId}/messages`
- AI: `POST /api/v1/ai/chat`

