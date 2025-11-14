package main

import (
	"log"
	"os"

	"github.com/gin-gonic/gin"
	"github.com/kent-app/server/internal/api"
	"github.com/kent-app/server/internal/config"
	"github.com/kent-app/server/internal/database"
)

func main() {
	cfg := config.Load()

	// Initialize database
	db, err := database.NewPostgres(cfg.DatabaseURL)
	if err != nil {
		log.Fatalf("Failed to connect to database: %v", err)
	}
	defer db.Close()

	// Initialize Redis
	redisClient := database.NewRedis(cfg.RedisURL)

	// Setup router
	router := gin.Default()
	api.SetupRoutes(router, db, redisClient, cfg)

	port := os.Getenv("PORT")
	if port == "" {
		port = "8080"
	}

	log.Printf("Starting server on port %s", port)
	if err := router.Run(":" + port); err != nil {
		log.Fatalf("Failed to start server: %v", err)
	}
}

