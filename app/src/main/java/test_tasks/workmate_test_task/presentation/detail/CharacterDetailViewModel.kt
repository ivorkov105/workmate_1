package test_tasks.workmate_test_task.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import test_tasks.workmate_test_task.domain.usecase.GetCharacterUseCase
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val characterId: Int = checkNotNull(savedStateHandle["characterId"])

    val uiState: StateFlow<CharacterDetailUiState> = getCharacterUseCase(characterId)
        .map { character ->
            if (character != null) {
                CharacterDetailUiState.Success(character)
            } else {
                CharacterDetailUiState.Error("Character not found")
            }
        }
        .onStart { emit(CharacterDetailUiState.Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CharacterDetailUiState.Loading
        )
}
