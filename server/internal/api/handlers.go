package api

import (
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
	"github.com/google/uuid"
	"github.com/kent-app/server/internal/config"
	"github.com/kent-app/server/internal/database"
)

type SendOtpRequest struct {
	PhoneNumber string `json:"phoneNumber" binding:"required"`
}

type VerifyOtpRequest struct {
	PhoneNumber string `json:"phoneNumber" binding:"required"`
	Code        string `json:"code" binding:"required"`
}

func handleSendOtp(db *database.PostgresDB, redis *database.RedisClient) gin.HandlerFunc {
	return func(c *gin.Context) {
		var req SendOtpRequest
		if err := c.ShouldBindJSON(&req); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		// Generate OTP (6 digits)
		otp := generateOTP()

		// Store in Redis with 5 minute TTL
		key := "otp:" + req.PhoneNumber
		redis.Set(c.Request.Context(), key, otp, 5*time.Minute)

		// TODO: Send via SMS/Voice gateway (Infobip, MessageBird, etc.)

		c.JSON(http.StatusOK, gin.H{
			"success":   true,
			"expiresIn": 300,
		})
	}
}

func handleVerifyOtp(
	db *database.PostgresDB,
	redis *database.RedisClient,
	cfg *config.Config,
) gin.HandlerFunc {
	return func(c *gin.Context) {
		var req VerifyOtpRequest
		if err := c.ShouldBindJSON(&req); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		// Verify OTP from Redis
		key := "otp:" + req.PhoneNumber
		storedOtp, err := redis.Get(c.Request.Context(), key).Result()
		if err != nil || storedOtp != req.Code {
			c.JSON(http.StatusUnauthorized, gin.H{"error": "Invalid OTP"})
			return
		}

		// Delete OTP
		redis.Del(c.Request.Context(), key)

		// Get or create user
		var userID uuid.UUID
		err = db.QueryRow(
			"SELECT id FROM users WHERE phone_number = $1",
			req.PhoneNumber,
		).Scan(&userID)

		if err != nil {
			// Create new user
			err = db.QueryRow(
				"INSERT INTO users (phone_number) VALUES ($1) RETURNING id",
				req.PhoneNumber,
			).Scan(&userID)
			if err != nil {
				c.JSON(http.StatusInternalServerError, gin.H{"error": "Failed to create user"})
				return
			}
		}

		// Generate JWT tokens
		accessToken := generateJWT(userID.String(), cfg.JWTSecret, 24*time.Hour)
		refreshToken := generateJWT(userID.String(), cfg.JWTSecret, 7*24*time.Hour)

		c.JSON(http.StatusOK, gin.H{
			"accessToken":  accessToken,
			"refreshToken": refreshToken,
			"user": gin.H{
				"id":          userID.String(),
				"phoneNumber": req.PhoneNumber,
			},
		})
	}
}

func handleGetChats(db *database.PostgresDB) gin.HandlerFunc {
	return func(c *gin.Context) {
		userID := c.GetString("userID")

		rows, err := db.Query(`
			SELECT c.id, c.type, c.updated_at
			FROM chats c
			INNER JOIN chat_participants cp ON c.id = cp.chat_id
			WHERE cp.user_id = $1
			ORDER BY c.updated_at DESC
		`, userID)

		if err != nil {
			c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
			return
		}
		defer rows.Close()

		chats := []gin.H{}
		for rows.Next() {
			var chatID, chatType string
			var updatedAt time.Time
			rows.Scan(&chatID, &chatType, &updatedAt)

			chats = append(chats, gin.H{
				"id":        chatID,
				"type":      chatType,
				"updatedAt": updatedAt.Unix(),
			})
		}

		c.JSON(http.StatusOK, chats)
	}
}

func handleCreateChat(db *database.PostgresDB) gin.HandlerFunc {
	return func(c *gin.Context) {
		// TODO: Implement
		c.JSON(http.StatusOK, gin.H{"message": "Not implemented"})
	}
}

func handleGetMessages(db *database.PostgresDB) gin.HandlerFunc {
	return func(c *gin.Context) {
		// TODO: Implement
		c.JSON(http.StatusOK, []gin.H{})
	}
}

func handleSendMessage(db *database.PostgresDB) gin.HandlerFunc {
	return func(c *gin.Context) {
		// TODO: Implement
		c.JSON(http.StatusOK, gin.H{"message": "Not implemented"})
	}
}

func handleAiChat(redis *database.RedisClient) gin.HandlerFunc {
	return func(c *gin.Context) {
		// TODO: Integrate with AI service
		c.JSON(http.StatusOK, gin.H{
			"response":   "AI response placeholder",
			"tokensUsed": 0,
		})
	}
}

// Helper functions
func generateOTP() string {
	// Simple 6-digit OTP
	return "123456" // TODO: Use crypto/rand
}

func generateJWT(userID, secret string, expiry time.Duration) string {
	// TODO: Implement JWT generation
	return "jwt-token-placeholder"
}

