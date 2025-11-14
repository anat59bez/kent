package com.kent.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _chats = MutableStateFlow<List<ChatItem>>(emptyList())
    val chats: StateFlow<List<ChatItem>> = _chats

    init {
        loadChats()
    }

    private fun loadChats() {
        viewModelScope.launch {
            // TODO: Load from API
            _chats.value = listOf(
                ChatItem("1", "Alice", "Hello!", "12:34"),
                ChatItem("2", "Bob", "How are you?", "11:20"),
                ChatItem("3", "Charlie", "See you later!", "10:15")
            )
        }
    }
}

data class ChatItem(
    val id: String,
    val name: String,
    val lastMessage: String,
    val timestamp: String
)

