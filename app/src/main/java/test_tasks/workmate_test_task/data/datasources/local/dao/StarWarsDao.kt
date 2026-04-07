package test_tasks.workmate_test_task.data.datasources.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import test_tasks.workmate_test_task.data.datasources.local.entities.*
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.*

@Dao
interface StarWarsDao {

    // --- Characters ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characters")
    fun getAllCharactersWithDetails(): Flow<List<CharacterWithDetails>>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity?

    // --- Films ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<FilmEntity>)

    @Query("SELECT * FROM films")
    fun getAllFilmsWithDetails(): Flow<List<FilmWithDetails>>

    // --- Planets ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanets(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planets")
    fun getAllPlanets(): Flow<List<PlanetEntity>>

    // --- Species ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(species: List<SpeciesEntity>)

    // --- Vehicles ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicles(vehicles: List<VehicleEntity>)

    // --- Starships ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarships(starships: List<StarshipEntity>)

    // --- CrossRef Inserts ---
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

    // --- Complex Queries using Transactions ---
    
    @Transaction
    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacterWithDetails(id: Int): Flow<CharacterWithDetails?>

    @Transaction
    @Query("SELECT * FROM films WHERE id = :id")
    fun getFilmWithDetails(id: Int): Flow<FilmWithDetails?>
}
