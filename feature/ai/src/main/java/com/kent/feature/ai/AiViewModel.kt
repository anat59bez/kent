package com.kent.feature.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AiViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<AiMessage>>(emptyList())
    val messages: StateFlow<List<AiMessage>> = _messages

    fun sendMessage(text: String) {
        viewModelScope.launch {
            _messages.value = _messages.value + AiMessage(text, true)
            // TODO: Call AI API
            _messages.value = _messages.value + AiMessage("AI response placeholder", false)
        }
    }
}

data class AiMessage(
    val text: String,
    val isUser: Boolean
)

