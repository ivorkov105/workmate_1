package test_tasks.workmate_test_task.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test_tasks.workmate_test_task.data.datasources.local.dao.StarWarsDao
import test_tasks.workmate_test_task.data.datasources.remote.StarWarsApiClient
import test_tasks.workmate_test_task.data.mappers.*
import test_tasks.workmate_test_task.domain.model.Character
import test_tasks.workmate_test_task.domain.model.Film
import test_tasks.workmate_test_task.domain.repository.StarWarsRepository
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val apiService: StarWarsApiClient,
    private val starWarsDao: StarWarsDao
) : StarWarsRepository {

    override fun getCharacters(search: String?): Flow<List<Character>> {
        val flow = if (search.isNullOrBlank()) {
            starWarsDao.getAllCharactersWithDetails()
        } else {
            starWarsDao.searchCharactersWithDetails(search)
        }
        return flow.map { list -> list.map { it.toDomain() } }
    }

    override suspend fun syncCharacters(page: Int): Result<Unit> {
        return try {
            val response = apiService.getCharacters(page)
            val dtos = response.results
            
            val entities = dtos.map { it.toEntity() }
            starWarsDao.insertCharacters(entities)
            
            dtos.forEach { dto ->
                starWarsDao.insertCharacterFilmCrossRefs(dto.toFilmCrossRefs())
                starWarsDao.insertCharacterSpeciesCrossRefs(dto.toSpeciesCrossRefs())
                starWarsDao.insertCharacterVehicleCrossRefs(dto.toVehicleCrossRefs())
                starWarsDao.insertCharacterStarshipCrossRefs(dto.toStarshipCrossRefs())
            }
            
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getFilms(search: String?): Flow<List<Film>> {
        return starWarsDao.getAllFilmsWithDetails().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun syncFilms(): Result<Unit> {
        return try {
            val response = apiService.getFilms()
            val dtos = response.results
            
            val entities = dtos.map { it.toEntity() }
            starWarsDao.insertFilms(entities)
            
            dtos.forEach { dto ->
                starWarsDao.insertFilmPlanetCrossRefs(dto.toPlanetCrossRefs())
                starWarsDao.insertFilmSpeciesCrossRefs(dto.toSpeciesCrossRefs())
                starWarsDao.insertFilmVehicleCrossRefs(dto.toVehicleCrossRefs())
                starWarsDao.insertFilmStarshipCrossRefs(dto.toStarshipCrossRefs())
            }
            
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCharacter(id: Int): Flow<Character?> {
        return starWarsDao.getCharacterWithDetails(id).map { it?.toDomain() }
    }

    override fun getFilm(id: Int): Flow<Film?> {
        return starWarsDao.getFilmWithDetails(id).map { it?.toDomain() }
    }
}
