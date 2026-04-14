package test_tasks.workmate_test_task.data.datasources.local.entities.realations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import test_tasks.workmate_test_task.data.datasources.local.entities.CharacterEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.FilmEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.PlanetEntity

data class PlanetWithDetails(
    @Embedded val planet: PlanetEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = FilmPlanetCrossRef::class,
            parentColumn = "planetId",
            entityColumn = "filmId"
        )
    )
    val films: List<FilmEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "homeworldId"
    )
    val residents: List<CharacterEntity>
)
