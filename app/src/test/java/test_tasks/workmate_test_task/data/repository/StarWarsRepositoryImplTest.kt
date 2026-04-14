package test_tasks.workmate_test_task.data.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import test_tasks.workmate_test_task.data.datasources.local.dao.StarWarsDao
import test_tasks.workmate_test_task.data.datasources.local.entities.CharacterEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.PlanetEntity
import test_tasks.workmate_test_task.data.datasources.local.entities.realations.CharacterWithDetails
import test_tasks.workmate_test_task.data.datasources.remote.StarWarsApiClient
import test_tasks.workmate_test_task.data.datasources.remote.models.PlanetDto

class StarWarsRepositoryImplTest {

    private lateinit var apiService: StarWarsApiClient
    private lateinit var dao: StarWarsDao
    private lateinit var repository: StarWarsRepositoryImpl

    @Before
    fun setup() {
        apiService = mock(StarWarsApiClient::class.java)
        dao = mock(StarWarsDao::class.java)
        repository = StarWarsRepositoryImpl(apiService, dao)
    }

    @Test
    fun `getCharacter should return character from dao`() = runTest {
        val characterId = 1
        val characterEntity = CharacterEntity(
            id = characterId, name = "Luke", height = "172", mass = "77",
            hairColor = "blond", skinColor = "fair", eyeColor = "blue",
            birthYear = "19BBY", gender = "male", homeworldId = 1,
            created = "", edited = "", url = ""
        )
        val characterWithDetails = CharacterWithDetails(
            character = characterEntity,
            homeworld = null,
            films = emptyList(),
            species = emptyList(),
            vehicles = emptyList(),
            starships = emptyList()
        )
        
        `when`(dao.getCharacterWithDetails(characterId)).thenReturn(flowOf(characterWithDetails))

        val result = repository.getCharacter(characterId).first()

        verify(dao).getCharacterWithDetails(characterId)
        assertNotNull(result)
        assertEquals("Luke", result?.name)
    }

    @Test
    fun `syncPlanet should fetch from api and insert to dao if not exists`() = runTest {
        val planetId = 1
        val planetDto = PlanetDto(
            name = "Tatooine",
            rotationPeriod = "23",
            orbitalPeriod = "304",
            diameter = "10465",
            climate = "arid",
            gravity = "1 standard",
            terrain = "desert",
            surfaceWater = "1",
            population = "200000",
            residents = emptyList(),
            films = emptyList(),
            created = "",
            edited = "",
            url = "https://swapi.dev/api/planets/1/"
        )
        
        `when`(dao.getPlanetById(planetId)).thenReturn(null)
        `when`(apiService.getPlanet(planetId)).thenReturn(planetDto)

        repository.syncPlanet(planetId)

        verify(apiService).getPlanet(planetId)
        verify(dao).insertPlanets(anyList())
    }

    @Test
    fun `syncPlanet should not fetch from api if already exists in dao`() = runTest {
        val planetId = 1
        val planetEntity = PlanetEntity(
            id = planetId, name = "Tatooine", rotationPeriod = "", orbitalPeriod = "",
            diameter = "", climate = "", gravity = "", terrain = "", surfaceWater = "",
            population = "", created = "", edited = "", url = ""
        )
        `when`(dao.getPlanetById(planetId)).thenReturn(planetEntity)

        repository.syncPlanet(planetId)

        verify(apiService, never()).getPlanet(anyInt())
        verify(dao, never()).insertPlanets(anyList())
    }
}
