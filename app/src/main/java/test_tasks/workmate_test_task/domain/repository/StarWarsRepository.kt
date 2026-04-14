package test_tasks.workmate_test_task.domain.repository

import kotlinx.coroutines.flow.Flow
import test_tasks.workmate_test_task.domain.model.*

interface StarWarsRepository {
    fun getCharacters(search: String?): Flow<List<Character>>
    suspend fun syncCharacters(page: Int): Result<Unit>
    
    fun getFilms(search: String?): Flow<List<Film>>
    suspend fun syncFilms(): Result<Unit>
    
    fun getCharacter(id: Int): Flow<Character?>
    fun getFilm(id: Int): Flow<Film?>

    suspend fun syncCharacter(id: Int)
    suspend fun syncFilm(id: Int)
    suspend fun syncPlanet(id: Int)
    suspend fun syncSpecies(id: Int)
    suspend fun syncVehicle(id: Int)
    suspend fun syncStarship(id: Int)
}
