package test_tasks.workmate_test_task.data.datasources.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import test_tasks.workmate_test_task.data.datasources.local.entities.*
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.*

@Dao
interface StarWarsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Transaction
    @Query("SELECT * FROM characters WHERE name LIKE '%' || :query || '%' OR :query IS NULL")
    fun searchCharactersWithDetails(query: String?): Flow<List<CharacterWithDetails>>

    @Query("SELECT * FROM characters")
    fun getAllCharactersWithDetails(): Flow<List<CharacterWithDetails>>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<FilmEntity>)

    @Transaction
    @Query("SELECT * FROM films")
    fun getAllFilmsWithDetails(): Flow<List<FilmWithDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanets(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planets")
    fun getAllPlanets(): Flow<List<PlanetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(species: List<SpeciesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicles(vehicles: List<VehicleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarships(starships: List<StarshipEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterFilmCrossRefs(crossRefs: List<CharacterFilmCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterSpeciesCrossRefs(crossRefs: List<CharacterSpeciesCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterVehicleCrossRefs(crossRefs: List<CharacterVehicleCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterStarshipCrossRefs(crossRefs: List<CharacterStarshipCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmPlanetCrossRefs(crossRefs: List<FilmPlanetCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmSpeciesCrossRefs(crossRefs: List<FilmSpeciesCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmVehicleCrossRefs(crossRefs: List<FilmVehicleCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmStarshipCrossRefs(crossRefs: List<FilmStarshipCrossRef>)

    @Transaction
    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacterWithDetails(id: Int): Flow<CharacterWithDetails?>

    @Transaction
    @Query("SELECT * FROM films WHERE id = :id")
    fun getFilmWithDetails(id: Int): Flow<FilmWithDetails?>
}
