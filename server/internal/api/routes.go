package api

import (
	"github.com/gin-gonic/gin"
	"github.com/kent-app/server/internal/config"
	"github.com/kent-app/server/internal/database"
)

func SetupRoutes(
	router *gin.Engine,
	db *database.PostgresDB,
	redis *database.RedisClient,
	cfg *config.Config,
) {
	v1 := router.Group("/api/v1")

	// Auth routes
	auth := v1.Group("/auth")
	{
		auth.POST("/send-otp", handleSendOtp(db, redis))
		auth.POST("/verify-otp", handleVerifyOtp(db, redis, cfg))
	}

	// Protected routes
	protected := v1.Group("")
	protected.Use(authMiddleware(cfg))
	{
		// Chats
		chats := protected.Group("/chats")
		{
			chats.GET("", handleGetChats(db))
			chats.POST("", handleCreateChat(db))
			chats.GET("/:chatId/messages", handleGetMessages(db))
			chats.POST("/:chatId/messages", handleSendMessage(db))
		}

		// AI
		ai := protected.Group("/ai")
		{
			ai.POST("/chat", handleAiChat(redis))
		}
	}

	// Health check
	router.GET("/health", func(c *gin.Context) {
		c.JSON(200, gin.H{"status": "ok"})
	})
}

