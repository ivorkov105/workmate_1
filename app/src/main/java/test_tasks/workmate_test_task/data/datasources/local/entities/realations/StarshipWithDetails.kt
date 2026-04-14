package test_tasks.workmate_test_task.data.datasources.local.entities.realations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import test_tasks.workmate_test_task.data.datasources.local.entities.CharacterEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.FilmEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.StarshipEntity

data class StarshipWithDetails(
    @Embedded val starship: StarshipEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = CharacterStarshipCrossRef::class,
            parentColumn = "starshipId",
            entityColumn = "characterId"
        )
    )
    val pilots: List<CharacterEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = FilmStarshipCrossRef::class,
            parentColumn = "starshipId",
            entityColumn = "filmId"
        )
    )
    val films: List<FilmEntity>
)
