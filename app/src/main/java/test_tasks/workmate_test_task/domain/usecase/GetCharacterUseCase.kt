package test_tasks.workmate_test_task.domain.usecase

import kotlinx.coroutines.flow.Flow
import test_tasks.workmate_test_task.domain.model.Character
import test_tasks.workmate_test_task.domain.repository.StarWarsRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {
    operator fun invoke(id: Int): Flow<Character?> {
        return repository.getCharacter(id)
    }
}
