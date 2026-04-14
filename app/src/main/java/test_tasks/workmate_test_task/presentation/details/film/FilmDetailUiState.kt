package test_tasks.workmate_test_task.presentation.details.film

import test_tasks.workmate_test_task.domain.model.Film

sealed interface FilmDetailUiState {
    object Loading : FilmDetailUiState
    data class Success(val film: Film) : FilmDetailUiState
    data class Error(val message: String) : FilmDetailUiState
}
