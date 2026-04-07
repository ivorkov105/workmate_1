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

data class CharacterWithDetails(
	@Embedded val character: CharacterEntity,

	@Relation(
        parentColumn = "homeworldId",
        entityColumn = "id"
    )
    val homeworld: PlanetEntity?,

	@Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
	        value = CharacterFilmCrossRef::class,
	        parentColumn = "characterId",
	        entityColumn = "filmId"
        )
    )
    val films: List<FilmEntity>,

	@Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
	        value = CharacterSpeciesCrossRef::class,
	        parentColumn = "characterId",
	        entityColumn = "speciesId"
        )
    )
    val species: List<SpeciesEntity>,

	@Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
	        value = CharacterVehicleCrossRef::class,
	        parentColumn = "characterId",
	        entityColumn = "vehicleId"
        )
    )
    val vehicles: List<VehicleEntity>,

	@Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
	        value = CharacterStarshipCrossRef::class,
	        parentColumn = "characterId",
	        entityColumn = "starshipId"
        )
    )
    val starships: List<StarshipEntity>
)