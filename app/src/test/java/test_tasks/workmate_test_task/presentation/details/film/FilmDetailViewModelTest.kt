package test_tasks.workmate_test_task.presentation.details.film

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
import test_tasks.workmate_test_task.domain.model.Film
import test_tasks.workmate_test_task.domain.usecase.GetFilmUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class FilmDetailViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var getFilmUseCase: GetFilmUseCase
    private lateinit var viewModel: FilmDetailViewModel

    private val mockFilm = Film(
        id = 1, title = "A New Hope", episodeId = 4, openingCrawl = "Crawl",
        director = "Lucas", producer = "Kurtz", releaseDate = "1977",
        characters = emptyList(), planets = emptyList(), starships = emptyList(),
        vehicles = emptyList(), species = emptyList(), url = ""
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getFilmUseCase = mock(GetFilmUseCase::class.java)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when film is found, uiState should eventually be Success`() = runTest {
        val filmId = 1
        `when`(getFilmUseCase(filmId)).thenReturn(flowOf(mockFilm))
        val savedStateHandle = SavedStateHandle(mapOf("filmId" to filmId))
        
        viewModel = FilmDetailViewModel(getFilmUseCase, savedStateHandle)

        backgroundScope.launch(testDispatcher) {
            viewModel.uiState.collect {}
        }

        val state = viewModel.uiState.first { it is FilmDetailUiState.Success }
        assertTrue(state is FilmDetailUiState.Success)
        assertEquals(mockFilm, (state as FilmDetailUiState.Success).film)
    }

    @Test
    fun `when film is not found, uiState should eventually be Error`() = runTest {
        val filmId = 1
        `when`(getFilmUseCase(filmId)).thenReturn(flowOf(null))
        val savedStateHandle = SavedStateHandle(mapOf("filmId" to filmId))
        
        viewModel = FilmDetailViewModel(getFilmUseCase, savedStateHandle)

        backgroundScope.launch(testDispatcher) {
            viewModel.uiState.collect {}
        }

        val state = viewModel.uiState.first { it is FilmDetailUiState.Error }
        assertTrue(state is FilmDetailUiState.Error)
        assertEquals("Film not found", (state as FilmDetailUiState.Error).message)
    }
}
