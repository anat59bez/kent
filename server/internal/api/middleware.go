package api

import (
	"net/http"
	"strings"

	"github.com/gin-gonic/gin"
	"github.com/kent-app/server/internal/config"
)

func authMiddleware(cfg *config.Config) gin.HandlerFunc {
	return func(c *gin.Context) {
		authHeader := c.GetHeader("Authorization")
		if authHeader == "" {
			c.JSON(http.StatusUnauthorized, gin.H{"error": "Missing authorization header"})
			c.Abort()
			return
		}

		token := strings.TrimPrefix(authHeader, "Bearer ")
		if token == authHeader {
			c.JSON(http.StatusUnauthorized, gin.H{"error": "Invalid authorization header"})
			c.Abort()
			return
		}

		// TODO: Verify JWT token
		// For now, extract user ID from token
		userID := "user-id-placeholder"

		c.Set("userID", userID)
		c.Next()
	}
}

