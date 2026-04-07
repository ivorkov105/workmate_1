package test_tasks.workmate_test_task.data.datasources.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import test_tasks.workmate_test_task.data.datasources.local.entities.*
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.CharacterFilmCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.CharacterSpeciesCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.CharacterStarshipCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.CharacterVehicleCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.CharacterWithDetails
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.FilmPlanetCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.FilmSpeciesCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.FilmStarshipCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.FilmVehicleCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.FilmWithDetails

@Dao
interface StarWarsDao {

    //персонажи
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity?

    //фильмы
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<FilmEntity>)

    @Query("SELECT * FROM films")
    fun getAllFilms(): Flow<List<FilmEntity>>

    //планеты
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanets(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planets")
    fun getAllPlanets(): Flow<List<PlanetEntity>>

    //виды
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(species: List<SpeciesEntity>)

    //транспорт
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicles(vehicles: List<VehicleEntity>)

    //косм корабли
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
