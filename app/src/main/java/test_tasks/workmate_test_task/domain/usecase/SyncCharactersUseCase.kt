package test_tasks.workmate_test_task.domain.usecase

import test_tasks.workmate_test_task.domain.repository.StarWarsRepository
import javax.inject.Inject

class SyncCharactersUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {
    suspend operator fun invoke(page: Int): Result<Unit> {
        return repository.syncCharacters(page)
    }
}
