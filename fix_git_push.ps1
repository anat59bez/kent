# Скрипт для исправления ошибки "src refspec main does not match any"

Write-Host "=== Исправление проблемы с Git push ===" -ForegroundColor Green
Write-Host ""

$projectPath = "C:\Users\Vik\Desktop\kent"
Set-Location $projectPath

# Шаг 1: Проверка инициализации Git
Write-Host "1. Проверка Git репозитория..." -ForegroundColor Yellow
if (-not (Test-Path .git)) {
    Write-Host "   Инициализация Git..." -ForegroundColor Cyan
    git init
} else {
    Write-Host "   ✓ Git уже инициализирован" -ForegroundColor Green
}

# Шаг 2: Проверка наличия файлов
Write-Host "2. Проверка файлов..." -ForegroundColor Yellow
$files = Get-ChildItem -File -Recurse -Exclude ".git" | Measure-Object
if ($files.Count -eq 0) {
    Write-Host "   ⚠ Нет файлов для добавления!" -ForegroundColor Red
    Write-Host "   Убедитесь, что файлы проекта находятся в папке." -ForegroundColor Yellow
    exit 1
} else {
    Write-Host "   ✓ Найдено файлов: $($files.Count)" -ForegroundColor Green
}

# Шаг 3: Добавление файлов
Write-Host "3. Добавление файлов в Git..." -ForegroundColor Yellow
git add .
Write-Host "   ✓ Файлы добавлены" -ForegroundColor Green

# Шаг 4: Проверка статуса
Write-Host "4. Проверка статуса..." -ForegroundColor Yellow
$status = git status --short
if ($status) {
    Write-Host "   Есть изменения для commit" -ForegroundColor Cyan
} else {
    Write-Host "   Нет изменений" -ForegroundColor Yellow
}

# Шаг 5: Создание commit
Write-Host "5. Создание commit..." -ForegroundColor Yellow
$commitResult = git commit -m "Initial commit: Kent Messenger project structure" 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "   ✓ Commit создан успешно" -ForegroundColor Green
} else {
    Write-Host "   ⚠ Ошибка при создании commit:" -ForegroundColor Red
    Write-Host $commitResult -ForegroundColor Red
    
    # Проверка конфигурации Git
    Write-Host ""
    Write-Host "   Проверка конфигурации Git..." -ForegroundColor Yellow
    $userName = git config user.name
    $userEmail = git config user.email
    
    if (-not $userName -or -not $userEmail) {
        Write-Host "   ⚠ Git не настроен! Настройте имя и email:" -ForegroundColor Red
        Write-Host "   git config --global user.name 'Your Name'" -ForegroundColor Cyan
        Write-Host "   git config --global user.email 'your.email@example.com'" -ForegroundColor Cyan
        exit 1
    } else {
        Write-Host "   ✓ Git настроен: $userName <$userEmail>" -ForegroundColor Green
    }
}

# Шаг 6: Проверка веток
Write-Host "6. Проверка веток..." -ForegroundColor Yellow
$branches = git branch
Write-Host $branches -ForegroundColor Cyan

# Шаг 7: Переименование ветки в main (если нужно)
$currentBranch = git branch --show-current
if ($currentBranch -ne "main") {
    Write-Host "7. Переименование ветки в main..." -ForegroundColor Yellow
    if ($currentBranch) {
        git branch -M main
        Write-Host "   ✓ Ветка переименована в main" -ForegroundColor Green
    } else {
        # Если ветки нет, создаем main
        git checkout -b main
        Write-Host "   ✓ Создана ветка main" -ForegroundColor Green
    }
} else {
    Write-Host "7. ✓ Ветка уже называется main" -ForegroundColor Green
}

# Шаг 8: Настройка remote
Write-Host "8. Настройка remote репозитория..." -ForegroundColor Yellow
$remote = git remote get-url origin 2>$null
if (-not $remote) {
    git remote add origin https://github.com/anat59bez/kent.git
    Write-Host "   ✓ Remote добавлен" -ForegroundColor Green
} else {
    Write-Host "   ✓ Remote уже настроен: $remote" -ForegroundColor Green
}

# Шаг 9: Финальная проверка
Write-Host ""
Write-Host "=== Готово! ===" -ForegroundColor Green
Write-Host ""
Write-Host "Теперь выполните:" -ForegroundColor Yellow
Write-Host "  git push -u origin main" -ForegroundColor Cyan
Write-Host ""

# Показываем текущий статус
Write-Host "Текущий статус:" -ForegroundColor Yellow
git status

