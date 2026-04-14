package test_tasks.workmate_test_task.domain.usecase

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import test_tasks.workmate_test_task.domain.model.Film
import test_tasks.workmate_test_task.domain.repository.StarWarsRepository

class GetFilmUseCaseTest {

    private lateinit var repository: StarWarsRepository
    private lateinit var getFilmUseCase: GetFilmUseCase

    @Before
    fun setup() {
        repository = mock(StarWarsRepository::class.java)
        getFilmUseCase = GetFilmUseCase(repository)
    }

    @Test
    fun `invoke should call repository getFilm`() = runTest {
        val filmId = 1
        val mockFilm = mock(Film::class.java)
        `when`(repository.getFilm(filmId)).thenReturn(flowOf(mockFilm))

        val result = getFilmUseCase(filmId)
        
        verify(repository).getFilm(filmId)
        result.collect {
            assertEquals(mockFilm, it)
        }
    }
}
