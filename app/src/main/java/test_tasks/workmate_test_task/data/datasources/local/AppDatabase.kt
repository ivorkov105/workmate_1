package test_tasks.workmate_test_task.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import test_tasks.workmate_test_task.data.datasources.local.dao.StarWarsDao
import test_tasks.workmate_test_task.data.datasources.local.entities.*
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.CharacterFilmCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.CharacterSpeciesCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.CharacterStarshipCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.CharacterVehicleCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.FilmPlanetCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.FilmSpeciesCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.FilmStarshipCrossRef
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.FilmVehicleCrossRef

@Database(
    entities = [
        CharacterEntity::class,
        FilmEntity::class,
        PlanetEntity::class,
        SpeciesEntity::class,
        VehicleEntity::class,
        StarshipEntity::class,
        CharacterFilmCrossRef::class,
        CharacterSpeciesCrossRef::class,
        CharacterVehicleCrossRef::class,
        CharacterStarshipCrossRef::class,
        FilmPlanetCrossRef::class,
        FilmSpeciesCrossRef::class,
        FilmVehicleCrossRef::class,
        FilmStarshipCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun starWarsDao(): StarWarsDao
}
