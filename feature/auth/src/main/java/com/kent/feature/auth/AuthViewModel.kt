package com.kent.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun sendOtp(phoneNumber: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            // TODO: Call API
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                step = AuthStep.VERIFY
            )
        }
    }

    fun verifyOtp(code: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            // TODO: Verify code
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }
}

data class AuthUiState(
    val phoneNumber: String = "",
    val verificationCode: String = "",
    val step: AuthStep = AuthStep.PHONE,
    val isLoading: Boolean = false,
    val error: String? = null
)

enum class AuthStep {
    PHONE, VERIFY
}

