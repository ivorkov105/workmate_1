package test_tasks.workmate_test_task.domain.usecase

import kotlinx.coroutines.flow.Flow
import test_tasks.workmate_test_task.domain.model.Film
import test_tasks.workmate_test_task.domain.repository.StarWarsRepository
import javax.inject.Inject

class GetFilmUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {
    operator fun invoke(id: Int): Flow<Film?> {
        return repository.getFilm(id)
    }
}
