package com.kent.core.network

import kotlinx.serialization.Serializable
import retrofit2.http.*

/**
 * Main API service for Kent backend
 */
interface KentApiService {
    @POST("auth/send-otp")
    suspend fun sendOtp(@Body request: OtpRequest): OtpResponse

    @POST("auth/verify-otp")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): AuthResponse

    @GET("chats")
    suspend fun getChats(): List<ChatDto>

    @POST("chats")
    suspend fun createChat(@Body request: CreateChatRequest): ChatDto

    @GET("chats/{chatId}/messages")
    suspend fun getMessages(@Path("chatId") chatId: String): List<MessageDto>

    @POST("chats/{chatId}/messages")
    suspend fun sendMessage(
        @Path("chatId") chatId: String,
        @Body request: SendMessageRequest
    ): MessageDto

    @POST("ai/chat")
    suspend fun aiChat(@Body request: AiChatRequest): AiChatResponse
}

@Serializable
data class OtpRequest(val phoneNumber: String)

@Serializable
data class OtpResponse(val success: Boolean, val expiresIn: Int)

@Serializable
data class VerifyOtpRequest(val phoneNumber: String, val code: String)

@Serializable
data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val user: UserDto
)

@Serializable
data class UserDto(
    val id: String,
    val phoneNumber: String,
    val displayName: String?,
    val avatarUrl: String?
)

@Serializable
data class ChatDto(
    val id: String,
    val type: String, // "direct" | "group"
    val participants: List<UserDto>,
    val lastMessage: MessageDto?,
    val updatedAt: Long
)

@Serializable
data class MessageDto(
    val id: String,
    val chatId: String,
    val senderId: String,
    val content: String,
    val type: String, // "text" | "image" | "video" | "audio"
    val timestamp: Long,
    val encrypted: Boolean
)

@Serializable
data class CreateChatRequest(val participantId: String)

@Serializable
data class SendMessageRequest(
    val content: String,
    val type: String = "text",
    val encrypted: Boolean = true
)

@Serializable
data class AiChatRequest(val message: String, val context: Map<String, String>? = null)

@Serializable
data class AiChatResponse(val response: String, val tokensUsed: Int)

