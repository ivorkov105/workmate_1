package test_tasks.workmate_test_task.presentation.detail

import test_tasks.workmate_test_task.domain.model.Character

sealed interface CharacterDetailUiState {
    object Loading : CharacterDetailUiState
    data class Success(val character: Character) : CharacterDetailUiState
    data class Error(val message: String) : CharacterDetailUiState
}
