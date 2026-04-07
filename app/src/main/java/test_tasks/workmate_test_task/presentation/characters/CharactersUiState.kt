package test_tasks.workmate_test_task.presentation.characters

import test_tasks.workmate_test_task.domain.model.Character

sealed interface CharactersUiState {
    object Loading : CharactersUiState
    data class Success(
        val characters: List<Character>,
        val isSyncing: Boolean = false
    ) : CharactersUiState
    data class Error(val message: String) : CharactersUiState
    object Empty : CharactersUiState
}
