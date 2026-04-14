package test_tasks.workmate_test_task.data.mappers

import test_tasks.workmate_test_task.data.datasources.local.entities.*
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.*
import test_tasks.workmate_test_task.data.datasources.remote.models.*
import test_tasks.workmate_test_task.domain.model.*

fun String.toId(): Int {
    return this.split("/").filter { it.isNotEmpty() }.last().toInt()
}

// --- DTO to Entity ---

fun CharacterDto.toEntity(): CharacterEntity = CharacterEntity(
    id = url.toId(),
    name = name,
    height = height,
    mass = mass,
    hairColor = hairColor,
    skinColor = skinColor,
    eyeColor = eyeColor,
    birthYear = birthYear,
    gender = gender,
    homeworldId = homeworld.toId(),
    created = created,
    edited = edited,
    url = url
)

fun FilmDto.toEntity(): FilmEntity = FilmEntity(
    id = url.toId(),
    title = title,
    episodeId = episodeId,
    openingCrawl = openingCrawl,
    director = director,
    producer = producer,
    releaseDate = releaseDate,
    created = created,
    edited = edited,
    url = url
)

fun PlanetDto.toEntity(): PlanetEntity = PlanetEntity(
    id = url.toId(),
    name = name,
    rotationPeriod = rotationPeriod,
    orbitalPeriod = orbitalPeriod,
    diameter = diameter,
    climate = climate,
    gravity = gravity,
    terrain = terrain,
    surfaceWater = surfaceWater,
    population = population,
    created = created,
    edited = edited,
    url = url
)

fun SpeciesDto.toEntity(): SpeciesEntity = SpeciesEntity(
    id = url.toId(),
    name = name,
    classification = classification,
    designation = designation,
    averageHeight = averageHeight,
    skinColors = skinColors,
    hairColors = hairColors,
    eyeColors = eyeColors,
    averageLifespan = averageLifespan,
    homeworldId = homeworld?.toId(),
    language = language,
    created = created,
    edited = edited,
    url = url
)

fun VehicleDto.toEntity(): VehicleEntity = VehicleEntity(
    id = url.toId(),
    name = name,
    model = model,
    manufacturer = manufacturer,
    costInCredits = costInCredits,
    length = length,
    maxAtmospheringSpeed = maxAtmospheringSpeed,
    crew = crew,
    passengers = passengers,
    cargoCapacity = cargoCapacity,
    consumables = consumables,
    vehicleClass = vehicleClass,
    created = created,
    edited = edited,
    url = url
)

fun StarshipDto.toEntity(): StarshipEntity = StarshipEntity(
    id = url.toId(),
    name = name,
    model = model,
    manufacturer = manufacturer,
    costInCredits = costInCredits,
    length = length,
    maxAtmospheringSpeed = maxAtmospheringSpeed,
    crew = crew,
    passengers = passengers,
    cargoCapacity = cargoCapacity,
    consumables = consumables,
    hyperdriveRating = hyperdriveRating,
    MGLT = MGLT,
    starshipClass = starshipClass,
    created = created,
    edited = edited,
    url = url
)

// --- Entity to Domain ---

fun CharacterEntity.toDomain(): Character = Character(
    id = id,
    name = name,
    height = height,
    mass = mass,
    hairColor = hairColor,
    skinColor = skinColor,
    eyeColor = eyeColor,
    birthYear = birthYear,
    gender = gender,
    homeworld = null,
    films = emptyList(),
    species = emptyList(),
    vehicles = emptyList(),
    starships = emptyList(),
    url = url
)

fun CharacterWithDetails.toDomain(): Character = character.toDomain().copy(
    homeworld = homeworld?.toDomain(),
    films = films.map { it.toDomain() },
    species = species.map { it.toDomain() },
    vehicles = vehicles.map { it.toDomain() },
    starships = starships.map { it.toDomain() }
)

fun FilmEntity.toDomain(): Film = Film(
    id = id,
    title = title,
    episodeId = episodeId,
    openingCrawl = openingCrawl,
    director = director,
    producer = producer,
    releaseDate = releaseDate,
    characters = emptyList(),
    planets = emptyList(),
    starships = emptyList(),
    vehicles = emptyList(),
    species = emptyList(),
    url = url
)

fun FilmWithDetails.toDomain(): Film = film.toDomain().copy(
    characters = characters.map { it.toDomain() },
    planets = planets.map { it.toDomain() },
    starships = starships.map { it.toDomain() },
    vehicles = vehicles.map { it.toDomain() },
    species = species.map { it.toDomain() }
)

fun PlanetEntity.toDomain(): Planet = Planet(
    id = id,
    name = name,
    rotationPeriod = rotationPeriod,
    orbitalPeriod = orbitalPeriod,
    diameter = diameter,
    climate = climate,
    gravity = gravity,
    terrain = terrain,
    surfaceWater = surfaceWater,
    population = population,
    residents = emptyList(),
    films = emptyList(),
    url = url
)

fun PlanetWithDetails.toDomain(): Planet = planet.toDomain().copy(
    residents = residents.map { it.toDomain() },
    films = films.map { it.toDomain() }
)

fun SpeciesEntity.toDomain(): Species = Species(
    id = id,
    name = name,
    classification = classification,
    designation = designation,
    averageHeight = averageHeight,
    skinColors = skinColors,
    hairColors = hairColors,
    eyeColors = eyeColors,
    averageLifespan = averageLifespan,
    homeworld = null,
    language = language,
    people = emptyList(),
    films = emptyList(),
    url = url
)

fun SpeciesWithDetails.toDomain(): Species = species.toDomain().copy(
    homeworld = homeworld?.toDomain(),
    people = people.map { it.toDomain() },
    films = films.map { it.toDomain() }
)

fun VehicleEntity.toDomain(): Vehicle = Vehicle(
    id = id,
    name = name,
    model = model,
    manufacturer = manufacturer,
    costInCredits = costInCredits,
    length = length,
    maxAtmospheringSpeed = maxAtmospheringSpeed,
    crew = crew,
    passengers = passengers,
    cargoCapacity = cargoCapacity,
    consumables = consumables,
    vehicleClass = vehicleClass,
    pilots = emptyList(),
    films = emptyList(),
    url = url
)

fun VehicleWithDetails.toDomain(): Vehicle = vehicle.toDomain().copy(
    pilots = pilots.map { it.toDomain() },
    films = films.map { it.toDomain() }
)

fun StarshipEntity.toDomain(): Starship = Starship(
    id = id,
    name = name,
    model = model,
    manufacturer = manufacturer,
    costInCredits = costInCredits,
    length = length,
    maxAtmospheringSpeed = maxAtmospheringSpeed,
    crew = crew,
    passengers = passengers,
    cargoCapacity = cargoCapacity,
    consumables = consumables,
    hyperdriveRating = hyperdriveRating,
    MGLT = MGLT,
    starshipClass = starshipClass,
    pilots = emptyList(),
    films = emptyList(),
    url = url
)

fun StarshipWithDetails.toDomain(): Starship = starship.toDomain().copy(
    pilots = pilots.map { it.toDomain() },
    films = films.map { it.toDomain() }
)

// --- CrossRef Mappers ---

fun CharacterDto.toFilmCrossRefs(): List<CharacterFilmCrossRef> {
    val characterId = url.toId()
    return films.map { CharacterFilmCrossRef(characterId, it.toId()) }
}

fun CharacterDto.toSpeciesCrossRefs(): List<CharacterSpeciesCrossRef> {
    val characterId = url.toId()
    return species.map { CharacterSpeciesCrossRef(characterId, it.toId()) }
}

fun CharacterDto.toVehicleCrossRefs(): List<CharacterVehicleCrossRef> {
    val characterId = url.toId()
    return vehicles.map { CharacterVehicleCrossRef(characterId, it.toId()) }
}

fun CharacterDto.toStarshipCrossRefs(): List<CharacterStarshipCrossRef> {
    val characterId = url.toId()
    return starships.map { CharacterStarshipCrossRef(characterId, it.toId()) }
}

fun FilmDto.toPlanetCrossRefs(): List<FilmPlanetCrossRef> {
    val filmId = url.toId()
    return planets.map { FilmPlanetCrossRef(filmId, it.toId()) }
}

fun FilmDto.toSpeciesCrossRefs(): List<FilmSpeciesCrossRef> {
    val filmId = url.toId()
    return species.map { FilmSpeciesCrossRef(filmId, it.toId()) }
}

fun FilmDto.toVehicleCrossRefs(): List<FilmVehicleCrossRef> {
    val filmId = url.toId()
    return vehicles.map { FilmVehicleCrossRef(filmId, it.toId()) }
}

fun FilmDto.toStarshipCrossRefs(): List<FilmStarshipCrossRef> {
    val filmId = url.toId()
    return starships.map { FilmStarshipCrossRef(filmId, it.toId()) }
}
