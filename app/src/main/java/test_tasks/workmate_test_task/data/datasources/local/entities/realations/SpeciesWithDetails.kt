package test_tasks.workmate_test_task.data.datasources.local.entities.realations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import test_tasks.workmate_test_task.data.datasources.local.entities.CharacterEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.FilmEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.PlanetEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.SpeciesEntity

data class SpeciesWithDetails(
    @Embedded val species: SpeciesEntity,

    @Relation(
        parentColumn = "homeworldId",
        entityColumn = "id"
    )
    val homeworld: PlanetEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = CharacterSpeciesCrossRef::class,
            parentColumn = "speciesId",
            entityColumn = "characterId"
        )
    )
    val people: List<CharacterEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = FilmSpeciesCrossRef::class,
            parentColumn = "speciesId",
            entityColumn = "filmId"
        )
    )
    val films: List<FilmEntity>
)
