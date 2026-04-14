package test_tasks.workmate_test_task.domain.usecase

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import test_tasks.workmate_test_task.domain.model.Character
import test_tasks.workmate_test_task.domain.repository.StarWarsRepository

class GetCharacterUseCaseTest {

    private lateinit var repository: StarWarsRepository
    private lateinit var getCharacterUseCase: GetCharacterUseCase

    @Before
    fun setup() {
        repository = mock(StarWarsRepository::class.java)
        getCharacterUseCase = GetCharacterUseCase(repository)
    }

    @Test
    fun `invoke should call repository getCharacter`() = runTest {
        val characterId = 1
        val mockCharacter = mock(Character::class.java)
        `when`(repository.getCharacter(characterId)).thenReturn(flowOf(mockCharacter))

        val result = getCharacterUseCase(characterId)
        
        verify(repository).getCharacter(characterId)
        result.collect {
            assertEquals(mockCharacter, it)
        }
    }
}
