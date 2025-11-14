# Скрипт для загрузки проекта Kent на GitHub
# Запустите: .\upload_to_github.ps1

Write-Host "=== Загрузка проекта Kent на GitHub ===" -ForegroundColor Green
Write-Host ""

# Проверка Git
Write-Host "Проверка Git..." -ForegroundColor Yellow
try {
    $gitVersion = git --version
    Write-Host "✓ Git установлен: $gitVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Git не установлен! Установите Git с https://git-scm.com/download/win" -ForegroundColor Red
    exit 1
}

# Переход в директорию проекта
$projectPath = "C:\Users\Vik\Desktop\kent"
Set-Location $projectPath
Write-Host "Текущая директория: $(Get-Location)" -ForegroundColor Cyan

# Инициализация Git (если еще не инициализирован)
if (-not (Test-Path .git)) {
    Write-Host "Инициализация Git репозитория..." -ForegroundColor Yellow
    git init
    Write-Host "✓ Git репозиторий инициализирован" -ForegroundColor Green
} else {
    Write-Host "✓ Git репозиторий уже инициализирован" -ForegroundColor Green
}

# Добавление файлов
Write-Host "Добавление файлов..." -ForegroundColor Yellow
git add .
Write-Host "✓ Файлы добавлены" -ForegroundColor Green

# Проверка изменений
$status = git status --short
if ($status) {
    Write-Host "Создание commit..." -ForegroundColor Yellow
    git commit -m "Initial commit: Kent Messenger project structure"
    Write-Host "✓ Commit создан" -ForegroundColor Green
} else {
    Write-Host "Нет изменений для commit" -ForegroundColor Yellow
}

# Добавление remote (если еще не добавлен)
$remote = git remote get-url origin 2>$null
if (-not $remote) {
    Write-Host "Добавление remote репозитория..." -ForegroundColor Yellow
    git remote add origin https://github.com/anat59bez/kent.git
    Write-Host "✓ Remote добавлен" -ForegroundColor Green
} else {
    Write-Host "✓ Remote уже настроен: $remote" -ForegroundColor Green
}

# Переименование ветки в main
Write-Host "Настройка ветки main..." -ForegroundColor Yellow
git branch -M main
Write-Host "✓ Ветка настроена" -ForegroundColor Green

# Инструкции для push
Write-Host ""
Write-Host "=== Готово к загрузке! ===" -ForegroundColor Green
Write-Host ""
Write-Host "Выполните следующую команду для загрузки:" -ForegroundColor Yellow
Write-Host "  git push -u origin main" -ForegroundColor Cyan
Write-Host ""
Write-Host "Примечание: GitHub может запросить авторизацию." -ForegroundColor Yellow
Write-Host "Используйте Personal Access Token вместо пароля." -ForegroundColor Yellow
Write-Host ""
Write-Host "Для создания токена: https://github.com/settings/tokens" -ForegroundColor Cyan

