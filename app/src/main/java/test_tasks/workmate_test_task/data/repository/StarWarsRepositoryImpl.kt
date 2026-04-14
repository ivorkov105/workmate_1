package test_tasks.workmate_test_task.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test_tasks.workmate_test_task.data.datasources.local.dao.StarWarsDao
import test_tasks.workmate_test_task.data.datasources.remote.StarWarsApiClient
import test_tasks.workmate_test_task.data.mappers.*
import test_tasks.workmate_test_task.domain.model.*
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
                
                syncPlanet(dto.homeworld.toId())
                dto.films.forEach { syncFilm(it.toId()) }
                dto.species.forEach { syncSpecies(it.toId()) }
                dto.vehicles.forEach { syncVehicle(it.toId()) }
                dto.starships.forEach { syncStarship(it.toId()) }
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
                
                dto.planets.forEach { syncPlanet(it.toId()) }
                dto.characters.forEach { syncCharacter(it.toId()) }
                dto.species.forEach { syncSpecies(it.toId()) }
                dto.vehicles.forEach { syncVehicle(it.toId()) }
                dto.starships.forEach { syncStarship(it.toId()) }
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

    override suspend fun syncCharacter(id: Int) {
        if (starWarsDao.getCharacterById(id) != null) return
        try {
            val dto = apiService.getCharacter(id)
            starWarsDao.insertCharacters(listOf(dto.toEntity()))
            starWarsDao.insertCharacterFilmCrossRefs(dto.toFilmCrossRefs())
            starWarsDao.insertCharacterSpeciesCrossRefs(dto.toSpeciesCrossRefs())
            starWarsDao.insertCharacterVehicleCrossRefs(dto.toVehicleCrossRefs())
            starWarsDao.insertCharacterStarshipCrossRefs(dto.toStarshipCrossRefs())
        } catch (e: Exception) { }
    }

    override suspend fun syncFilm(id: Int) {
        if (starWarsDao.getFilmById(id) != null) return
        try {
            val dto = apiService.getFilm(id)
            starWarsDao.insertFilms(listOf(dto.toEntity()))
            starWarsDao.insertFilmPlanetCrossRefs(dto.toPlanetCrossRefs())
            starWarsDao.insertFilmSpeciesCrossRefs(dto.toSpeciesCrossRefs())
            starWarsDao.insertFilmVehicleCrossRefs(dto.toVehicleCrossRefs())
            starWarsDao.insertFilmStarshipCrossRefs(dto.toStarshipCrossRefs())
        } catch (e: Exception) { }
    }

    override suspend fun syncPlanet(id: Int) {
        if (starWarsDao.getPlanetById(id) != null) return
        try {
            val dto = apiService.getPlanet(id)
            starWarsDao.insertPlanets(listOf(dto.toEntity()))
        } catch (e: Exception) { }
    }

    override suspend fun syncSpecies(id: Int) {
        if (starWarsDao.getSpeciesById(id) != null) return
        try {
            val dto = apiService.getSpeciesItem(id)
            starWarsDao.insertSpecies(listOf(dto.toEntity()))
        } catch (e: Exception) { }
    }

    override suspend fun syncVehicle(id: Int) {
        if (starWarsDao.getVehicleById(id) != null) return
        try {
            val dto = apiService.getVehicle(id)
            starWarsDao.insertVehicles(listOf(dto.toEntity()))
        } catch (e: Exception) { }
    }

    override suspend fun syncStarship(id: Int) {
        if (starWarsDao.getStarshipById(id) != null) return
        try {
            val dto = apiService.getStarship(id)
            starWarsDao.insertStarships(listOf(dto.toEntity()))
        } catch (e: Exception) { }
    }
}
