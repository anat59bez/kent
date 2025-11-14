# Kent Messenger

**Brand Palette: Azure** | Package: `com.kent.app`

Современный мессенджер с AI-ассистентом и максимальной безопасностью.

## 🚀 Быстрый старт

### Android

1. **Открыть в Android Studio**
   - File → Open → выбрать папку `C:\Users\Vik\Desktop\kent`
   - Дождаться синхронизации Gradle

2. **Собрать проект**
```bash
./gradlew build
```

3. **Запустить на устройстве/эмуляторе**

### Сервер (Go)

1. **Запустить через Docker Compose**
```bash
cd server
docker-compose up -d
```

---

## 📁 Структура проекта

```
kent/
├── app/                    # Главный модуль приложения
├── core/                   # Core модули
│   ├── ui/                 # Theme, компоненты
│   ├── network/            # API клиенты
│   ├── crypto/             # E2EE, шифрование
│   ├── database/           # Room, локальная БД
│   └── common/             # Утилиты
├── feature/                # Feature модули
│   ├── auth/               # Регистрация, OTP
│   ├── chat/               # Чаты, сообщения
│   ├── calls/              # Звонки (WebRTC)
│   ├── stories/            # Сторис
│   ├── ai/                 # Kent.AI
│   ├── settings/           # Настройки
│   └── profile/            # Профиль
├── server/                 # Go сервер
└── docs/                   # Документация
```

---

## 📚 Документация

См. папку `docs/`:
- `ARCHITECTURE.md` — архитектура
- `API.md` — API спецификация
- `SECURITY.md` — безопасность
- `GOOGLE_PLAY.md` — Google Play
- `GETTING_STARTED.md` — быстрый старт

---

**Проект находится в:** `C:\Users\Vik\Desktop\kent`

