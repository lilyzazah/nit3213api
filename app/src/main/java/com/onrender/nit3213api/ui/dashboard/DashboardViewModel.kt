package com.onrender.nit3213api.ui.dashboard

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repo: AuthRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    fun loadDashboard(keypass: String) = viewModelScope.launch {
        _uiState.value = DashboardUiState.Loading
        when (val res = repo.getDashboard(keypass)) {
            is Result.Success -> _uiState.value = DashboardUiState.Success(res.getOrNull()!!)
            is Result.Failure -> _uiState.value = DashboardUiState.Error(res.exceptionOrNull()?.localizedMessage ?: "Error")
        }
    }
}

sealed interface DashboardUiState {
    object Loading : DashboardUiState
    data class Success(val data: DashboardResponse) : DashboardUiState
    data class Error(val message: String) : DashboardUiState
}
