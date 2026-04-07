package test_tasks.workmate_test_task.domain.usecase

import kotlinx.coroutines.flow.Flow
import test_tasks.workmate_test_task.domain.model.Character
import test_tasks.workmate_test_task.domain.repository.StarWarsRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {
    operator fun invoke(search: String? = null): Flow<List<Character>> {
        return repository.getCharacters(search)
    }
}
