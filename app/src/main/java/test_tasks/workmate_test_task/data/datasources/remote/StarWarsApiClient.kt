package test_tasks.workmate_test_task.data.datasources.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import test_tasks.workmate_test_task.data.datasources.remote.models.*

interface StarWarsApiClient {

    @GET("people/")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("search") search: String? = null
    ): SwapiResponse<CharacterDto>

    @GET("people/{id}/")
    suspend fun getCharacter(@Path("id") id: Int): CharacterDto

    @GET("planets/")
    suspend fun getPlanets(
        @Query("page") page: Int? = null,
        @Query("search") search: String? = null
    ): SwapiResponse<PlanetDto>

    @GET("planets/{id}/")
    suspend fun getPlanet(@Path("id") id: Int): PlanetDto

    @GET("films/")
    suspend fun getFilms(
        @Query("page") page: Int? = null,
        @Query("search") search: String? = null
    ): SwapiResponse<FilmDto>

    @GET("films/{id}/")
    suspend fun getFilm(@Path("id") id: Int): FilmDto

    @GET("species/")
    suspend fun getSpecies(
        @Query("page") page: Int? = null
    ): SwapiResponse<SpeciesDto>

    @GET("species/{id}/")
    suspend fun getSpeciesItem(@Path("id") id: Int): SpeciesDto

    @GET("vehicles/")
    suspend fun getVehicles(
        @Query("page") page: Int? = null
    ): SwapiResponse<VehicleDto>

    @GET("vehicles/{id}/")
    suspend fun getVehicle(@Path("id") id: Int): VehicleDto

    @GET("starships/")
    suspend fun getStarships(
        @Query("page") page: Int? = null
    ): SwapiResponse<StarshipDto>

    @GET("starships/{id}/")
    suspend fun getStarship(@Path("id") id: Int): StarshipDto
}

data class SwapiResponse<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)
