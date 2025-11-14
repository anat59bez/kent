# Kent - Getting Started

## Android разработка

### Требования

- **Android Studio** Hedgehog (2023.1.1) или новее
- **JDK 17**
- **Android SDK** (API 26-34)
- **Gradle** 8.2+

### Первый запуск

1. **Открыть в Android Studio**
   - File → Open → выбрать папку `C:\Users\Vik\Desktop\kent`
   - Дождаться синхронизации Gradle

2. **Собрать проект**
```bash
./gradlew build
```

3. **Запустить на устройстве**
   - Подключить Android устройство (USB Debugging включен)
   - Или создать эмулятор (Android 8.0+)
   - Run → Run 'app'

---

## Серверная разработка

### Требования

- **Go** 1.21+
- **PostgreSQL** 15+
- **Redis** 7+
- **Docker** (опционально)

### Первый запуск

#### Вариант 1: Docker Compose (рекомендуется)

```bash
cd server
docker-compose up -d
```

Сервер будет доступен на `http://localhost:8080`

#### Вариант 2: Локально

1. **Настроить переменные окружения**
```bash
export DATABASE_URL="postgres://user:pass@localhost/kent?sslmode=disable"
export REDIS_URL="redis://localhost:6379"
export JWT_SECRET="your-secret-key"
```

2. **Запустить сервер**
```bash
go run cmd/api/main.go
```

---

**Проект готов к разработке!**

