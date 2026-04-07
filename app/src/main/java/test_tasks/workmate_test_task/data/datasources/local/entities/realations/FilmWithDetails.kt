package test_tasks.workmate_test_task.data.datasources.local.entities.realations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import test_tasks.workmate_test_task.data.datasources.local.entities.CharacterEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.FilmEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.PlanetEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.SpeciesEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.StarshipEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.VehicleEntity

data class FilmWithDetails(
	@Embedded val film: FilmEntity,

	@Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
	        value = CharacterFilmCrossRef::class,
	        parentColumn = "filmId",
	        entityColumn = "characterId"
        )
    )
    val characters: List<CharacterEntity>,

	@Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
	        value = FilmPlanetCrossRef::class,
	        parentColumn = "filmId",
	        entityColumn = "planetId"
        )
    )
    val planets: List<PlanetEntity>,

	@Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
	        value = FilmStarshipCrossRef::class,
	        parentColumn = "filmId",
	        entityColumn = "starshipId"
        )
    )
    val starships: List<StarshipEntity>,

	@Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
	        value = FilmVehicleCrossRef::class,
	        parentColumn = "filmId",
	        entityColumn = "vehicleId"
        )
    )
    val vehicles: List<VehicleEntity>,

	@Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
	        value = FilmSpeciesCrossRef::class,
	        parentColumn = "filmId",
	        entityColumn = "speciesId"
        )
    )
    val species: List<SpeciesEntity>
)