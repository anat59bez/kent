# Инструкция по загрузке проекта на GitHub

## Шаг 1: Проверьте наличие Git

Откройте PowerShell или Git Bash и выполните:
```bash
git --version
```

Если Git не установлен, скачайте с: https://git-scm.com/download/win

## Шаг 2: Инициализируйте Git репозиторий

```bash
cd C:\Users\Vik\Desktop\kent
git init
```

## Шаг 3: Добавьте все файлы

```bash
git add .
```

## Шаг 4: Создайте первый commit

```bash
git commit -m "Initial commit: Kent Messenger project structure"
```

## Шаг 5: Добавьте remote репозиторий

```bash
git remote add origin https://github.com/anat59bez/kent.git
```

## Шаг 6: Переименуйте ветку в main (если нужно)

```bash
git branch -M main
```

## Шаг 7: Загрузите на GitHub

```bash
git push -u origin main
```

**Примечание:** При первом push GitHub попросит вас авторизоваться. 
Используйте Personal Access Token вместо пароля.

---

## Альтернативный способ (через GitHub Desktop)

1. Установите GitHub Desktop: https://desktop.github.com/
2. Откройте GitHub Desktop
3. File → Add Local Repository → выберите `C:\Users\Vik\Desktop\kent`
4. Нажмите "Publish repository"
5. Выберите `anat59bez/kent` как название
6. Нажмите "Publish Repository"

---

## Если репозиторий уже существует на GitHub

Если репозиторий `anat59bez/kent` уже создан на GitHub, используйте:

```bash
git remote add origin https://github.com/anat59bez/kent.git
git branch -M main
git push -u origin main
```

Если возникнут конфликты:
```bash
git pull origin main --allow-unrelated-histories
git push -u origin main
```

