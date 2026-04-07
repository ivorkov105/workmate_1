package test_tasks.workmate_test_task.presentation.characters

sealed interface CharactersEvent {
    data class SearchQueryChanged(val query: String) : CharactersEvent
    data class CharacterClicked(val id: Int) : CharactersEvent
    object Refresh : CharactersEvent
}
