# Исправление ошибки "src refspec main does not match any"

## Проблема

Ошибка возникает, когда:
1. Нет ни одного commit в репозитории
2. Ветка `main` не существует
3. Git репозиторий не инициализирован

## Решение

### Вариант 1: Автоматический скрипт

```powershell
cd C:\Users\Vik\Desktop\kent
.\fix_git_push.ps1
```

### Вариант 2: Вручную

Выполните команды по порядку:

```powershell
cd C:\Users\Vik\Desktop\kent

# 1. Проверьте, что Git инициализирован
git status

# Если ошибка "not a git repository", выполните:
git init

# 2. Настройте Git (если еще не настроен)
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# 3. Добавьте все файлы
git add .

# 4. Создайте первый commit
git commit -m "Initial commit: Kent Messenger project"

# 5. Проверьте ветку
git branch

# Если ветки нет или она называется master, переименуйте:
git branch -M main

# Или создайте новую:
git checkout -b main

# 6. Добавьте remote (если еще не добавлен)
git remote add origin https://github.com/anat59bez/kent.git

# 7. Теперь можно пушить
git push -u origin main
```

### Вариант 3: Если файлов нет в папке

Если папка пустая или файлы были удалены:

1. Проверьте наличие файлов:
```powershell
Get-ChildItem -Recurse | Measure-Object
```

2. Если файлов нет, пересоздайте структуру проекта или восстановите из backup.

## Проверка после исправления

```powershell
# Проверьте статус
git status

# Проверьте ветки
git branch

# Проверьте remote
git remote -v

# Проверьте commit
git log --oneline
```

Если все настроено правильно, вы увидите:
- Ветку `main` (или `* main`)
- Remote `origin` указывающий на GitHub
- Хотя бы один commit

Тогда команда `git push -u origin main` должна работать!

