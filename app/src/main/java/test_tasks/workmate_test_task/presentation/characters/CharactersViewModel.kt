package test_tasks.workmate_test_task.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import test_tasks.workmate_test_task.domain.usecase.GetCharactersUseCase
import test_tasks.workmate_test_task.domain.usecase.SyncCharactersUseCase
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val syncCharactersUseCase: SyncCharactersUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isSyncing = MutableStateFlow(false)

    @OptIn(FlowPreview::class)
    val uiState: StateFlow<CharactersUiState> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            getCharactersUseCase(query.takeIf { it.isNotBlank() })
        }
        .combine(_isSyncing) { characters, isSyncing ->
            when {
                characters.isEmpty() && isSyncing -> CharactersUiState.Loading
                characters.isEmpty() -> CharactersUiState.Empty
                else -> CharactersUiState.Success(characters, isSyncing)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CharactersUiState.Loading
        )

    init {
        refresh()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun refresh() {
        viewModelScope.launch {
            _isSyncing.value = true
            syncCharactersUseCase(1)
            _isSyncing.value = false
        }
    }
}
