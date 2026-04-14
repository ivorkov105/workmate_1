package test_tasks.workmate_test_task.data.datasources.local.entities.realations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import test_tasks.workmate_test_task.data.datasources.local.entities.CharacterEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.FilmEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.VehicleEntity

data class VehicleWithDetails(
    @Embedded val vehicle: VehicleEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = CharacterVehicleCrossRef::class,
            parentColumn = "vehicleId",
            entityColumn = "characterId"
        )
    )
    val pilots: List<CharacterEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = FilmVehicleCrossRef::class,
            parentColumn = "vehicleId",
            entityColumn = "filmId"
        )
    )
    val films: List<FilmEntity>
)
