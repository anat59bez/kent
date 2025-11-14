# Скачивание gradle-wrapper.jar

## Проблема

Ошибка: `Could not find or load main class org.gradle.wrapper.GradleWrapperMain`

Это означает, что отсутствует файл `gradle/wrapper/gradle-wrapper.jar`.

## Решение

### Вариант 1: Автоматическое скачивание (рекомендуется)

Если у вас установлен Gradle:

```powershell
cd C:\Users\Vik\Desktop\kent
gradle wrapper --gradle-version 8.2
```

Это автоматически скачает `gradle-wrapper.jar`.

### Вариант 2: Ручное скачивание

1. Скачайте файл:
   - URL: https://raw.githubusercontent.com/gradle/gradle/v8.2.0/gradle/wrapper/gradle-wrapper.jar
   - Или: https://github.com/gradle/gradle/raw/v8.2.0/gradle/wrapper/gradle-wrapper.jar

2. Сохраните в папку:
   ```
   C:\Users\Vik\Desktop\kent\gradle\wrapper\gradle-wrapper.jar
   ```

3. Добавьте в Git:
   ```powershell
   git add gradle/wrapper/gradle-wrapper.jar
   git commit -m "Add gradle-wrapper.jar"
   git push
   ```

### Вариант 3: Через PowerShell (автоматически)

Выполните:

```powershell
cd C:\Users\Vik\Desktop\kent\gradle\wrapper
Invoke-WebRequest -Uri "https://raw.githubusercontent.com/gradle/gradle/v8.2.0/gradle/wrapper/gradle-wrapper.jar" -OutFile "gradle-wrapper.jar"
cd ..\..\..
git add gradle/wrapper/gradle-wrapper.jar
git commit -m "Add gradle-wrapper.jar"
git push
```

## Проверка

После скачивания проверьте:

```powershell
.\gradlew.bat --version
```

Должно показать версию Gradle 8.2.

## Примечание

`gradle-wrapper.jar` обычно не добавляют в Git (он в .gitignore), но для первого раза можно добавить, чтобы CI/CD работал сразу.

