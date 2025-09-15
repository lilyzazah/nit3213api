package com.onrender.nit3213api.ui.login

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: AuthRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login(location: String, username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            when (val res = repo.login(location, username, password)) {
                is Result.Success -> _uiState.value = LoginUiState.Success(res.getOrNull() ?: "")
                is Result.Failure -> {
                    val msg = res.exceptionOrNull()?.localizedMessage ?: "Unknown error"
                    _uiState.value = LoginUiState.Error(msg)
                }
            }
        }
    }
}

sealed interface LoginUiState {
    object Idle : LoginUiState
    object Loading : LoginUiState
    data class Success(val keypass: String) : LoginUiState
    data class Error(val message: String) : LoginUiState
}
