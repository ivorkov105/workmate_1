package test_tasks.workmate_test_task.presentation.details.character

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import test_tasks.workmate_test_task.domain.model.Character
import test_tasks.workmate_test_task.domain.usecase.GetCharacterUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var getCharacterUseCase: GetCharacterUseCase
    private lateinit var viewModel: CharacterDetailViewModel

    private val mockCharacter = Character(
        id = 1, name = "Luke", height = "172", mass = "77", hairColor = "blond",
        skinColor = "fair", eyeColor = "blue", birthYear = "19BBY", gender = "male",
        homeworld = null, films = emptyList(), species = emptyList(),
        vehicles = emptyList(), starships = emptyList(), url = ""
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getCharacterUseCase = mock(GetCharacterUseCase::class.java)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when character is found, uiState should eventually be Success`() = runTest {
        val characterId = 1
        `when`(getCharacterUseCase(characterId)).thenReturn(flowOf(mockCharacter))
        val savedStateHandle = SavedStateHandle(mapOf("characterId" to characterId))
        
        viewModel = CharacterDetailViewModel(getCharacterUseCase, savedStateHandle)

        backgroundScope.launch(testDispatcher) {
            viewModel.uiState.collect {}
        }

        val state = viewModel.uiState.first { it is CharacterDetailUiState.Success }
        assertTrue(state is CharacterDetailUiState.Success)
        assertEquals(mockCharacter, (state as CharacterDetailUiState.Success).character)
    }

    @Test
    fun `when character is not found, uiState should eventually be Error`() = runTest {
        val characterId = 1
        `when`(getCharacterUseCase(characterId)).thenReturn(flowOf(null))
        val savedStateHandle = SavedStateHandle(mapOf("characterId" to characterId))
        
        viewModel = CharacterDetailViewModel(getCharacterUseCase, savedStateHandle)

        backgroundScope.launch(testDispatcher) {
            viewModel.uiState.collect {}
        }

        val state = viewModel.uiState.first { it is CharacterDetailUiState.Error }
        assertTrue(state is CharacterDetailUiState.Error)
        assertEquals("Character not found", (state as CharacterDetailUiState.Error).message)
    }
}
