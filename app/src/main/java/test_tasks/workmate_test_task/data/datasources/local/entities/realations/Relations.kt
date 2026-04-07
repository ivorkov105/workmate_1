package test_tasks.workmate_test_task.data.datasources.local.entities.realations

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "character_film_cross_ref",
    primaryKeys = ["characterId", "filmId"],
    indices = [Index("filmId")]
)
data class CharacterFilmCrossRef(
    val characterId: Int,
    val filmId: Int
)

@Entity(
    tableName = "character_species_cross_ref",
    primaryKeys = ["characterId", "speciesId"],
    indices = [Index("speciesId")]
)
data class CharacterSpeciesCrossRef(
    val characterId: Int,
    val speciesId: Int
)

@Entity(
    tableName = "character_vehicle_cross_ref",
    primaryKeys = ["characterId", "vehicleId"],
    indices = [Index("vehicleId")]
)
data class CharacterVehicleCrossRef(
    val characterId: Int,
    val vehicleId: Int
)

@Entity(
    tableName = "character_starship_cross_ref",
    primaryKeys = ["characterId", "starshipId"],
    indices = [Index("starshipId")]
)
data class CharacterStarshipCrossRef(
    val characterId: Int,
    val starshipId: Int
)

@Entity(
    tableName = "film_planet_cross_ref",
    primaryKeys = ["filmId", "planetId"],
    indices = [Index("planetId")]
)
data class FilmPlanetCrossRef(
    val filmId: Int,
    val planetId: Int
)

@Entity(
    tableName = "film_species_cross_ref",
    primaryKeys = ["filmId", "speciesId"],
    indices = [Index("speciesId")]
)
data class FilmSpeciesCrossRef(
    val filmId: Int,
    val speciesId: Int
)

@Entity(
    tableName = "film_vehicle_cross_ref",
    primaryKeys = ["filmId", "vehicleId"],
    indices = [Index("vehicleId")]
)
data class FilmVehicleCrossRef(
    val filmId: Int,
    val vehicleId: Int
)

@Entity(
    tableName = "film_starship_cross_ref",
    primaryKeys = ["filmId", "starshipId"],
    indices = [Index("starshipId")]
)
data class FilmStarshipCrossRef(
    val filmId: Int,
    val starshipId: Int
)
