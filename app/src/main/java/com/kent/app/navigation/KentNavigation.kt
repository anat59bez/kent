package com.kent.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kent.feature.auth.AuthScreen
import com.kent.feature.chat.ChatListScreen
import com.kent.feature.ai.AiAssistantScreen

object NavDestinations {
    const val AUTH = "auth"
    const val CHAT_LIST = "chat_list"
    const val AI_ASSISTANT = "ai_assistant"
}

@Composable
fun KentNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavDestinations.AUTH
    ) {
        composable(NavDestinations.AUTH) {
            AuthScreen(
                onAuthSuccess = {
                    navController.navigate(NavDestinations.CHAT_LIST) {
                        popUpTo(NavDestinations.AUTH) { inclusive = true }
                    }
                }
            )
        }

        composable(NavDestinations.CHAT_LIST) {
            ChatListScreen(
                onNavigateToAi = {
                    navController.navigate(NavDestinations.AI_ASSISTANT)
                }
            )
        }

        composable(NavDestinations.AI_ASSISTANT) {
            AiAssistantScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

