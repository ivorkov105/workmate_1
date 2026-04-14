package test_tasks.workmate_test_task.presentation.details.film

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import test_tasks.workmate_test_task.domain.usecase.GetFilmUseCase
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val getFilmUseCase: GetFilmUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val filmId: Int = checkNotNull(savedStateHandle["filmId"])

    val uiState: StateFlow<FilmDetailUiState> = getFilmUseCase(filmId)
        .map { film ->
            if (film != null) {
                FilmDetailUiState.Success(film)
            } else {
                FilmDetailUiState.Error("Film not found")
            }
        }
        .onStart { emit(FilmDetailUiState.Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = FilmDetailUiState.Loading
        )
}
