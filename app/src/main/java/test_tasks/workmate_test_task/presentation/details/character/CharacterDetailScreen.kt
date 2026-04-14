package test_tasks.workmate_test_task.presentation.details.character

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import test_tasks.workmate_test_task.R
import test_tasks.workmate_test_task.domain.model.Character
import test_tasks.workmate_test_task.domain.model.Film
import test_tasks.workmate_test_task.domain.model.Planet
import test_tasks.workmate_test_task.ui.components.ClickableFilmCard
import test_tasks.workmate_test_task.ui.components.EmptyInfoCard
import test_tasks.workmate_test_task.ui.components.InfoCard
import test_tasks.workmate_test_task.ui.components.SectionTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
	viewModel: CharacterDetailViewModel,
	onBackClick: () -> Unit,
	onFilmClick: (Int) -> Unit
) {
	val uiState by viewModel.uiState.collectAsState()

	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					val title = (uiState as? CharacterDetailUiState.Success)?.character?.name ?: ""
					Text(title, color = Color(0xFF5C6BC0), fontWeight = FontWeight.Bold)
				},
				navigationIcon = {
					IconButton(onClick = onBackClick) {
						Icon(
							painter = painterResource(id = R.drawable.ic_arrow_back),
							contentDescription = "Back",
							tint = Color(0xFF5C6BC0)
						)
					}
				}
			)
		}
	) { padding ->
		when (val state = uiState) {
			is CharacterDetailUiState.Loading -> {
				Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
					CircularProgressIndicator(color = Color(0xFF5C6BC0))
				}
			}

			is CharacterDetailUiState.Success -> {
				CharacterDetailContent(
					character = state.character,
					onFilmClick = onFilmClick,
					modifier = Modifier.padding(padding)
				)
			}

			is CharacterDetailUiState.Error   -> {
				Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
					Text(state.message, color = MaterialTheme.colorScheme.error)
				}
			}
		}
	}
}

@Composable
fun CharacterDetailContent(
	character: Character,
	onFilmClick: (Int) -> Unit,
	modifier: Modifier = Modifier
) {
	LazyColumn(
		modifier = modifier
			.fillMaxSize()
			.padding(horizontal = 16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp),
		contentPadding = PaddingValues(vertical = 16.dp)
	) {
		item { SectionTitle("Basic Information") }
		item { InfoCard("Birth Year", character.birthYear) }
		item { InfoCard("Height", "${character.height}cm") }
		item { InfoCard("Mass", "${character.mass}kg") }
		item { InfoCard("Gender", character.gender) }
		item { InfoCard("Homeworld", character.homeworld?.name ?: "Unknown") }

		item { SectionTitle("Species") }
		if (character.species.isEmpty()) {
			item { EmptyInfoCard("No species information available") }
		} else {
			items(character.species) { species ->
				InfoCard(species.name, species.classification)
			}
		}

		item { SectionTitle("Films") }
		if (character.films.isEmpty()) {
			item { EmptyInfoCard("No films available") }
		} else {
			items(character.films) { film ->
				ClickableFilmCard(film.title) { onFilmClick(film.id) }
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun CharacterDetailContentPreview() {
	val mockPlanet = Planet(
		id = 1, name = "Tatooine", rotationPeriod = "23", orbitalPeriod = "304",
		diameter = "10465", climate = "arid", gravity = "1 standard", terrain = "desert",
		surfaceWater = "1", population = "200000", residents = emptyList(), films = emptyList(), url = ""
	)
	val mockFilm = Film(
		id = 1, title = "A New Hope", episodeId = 4, openingCrawl = "It is a period of civil war...",
		director = "George Lucas", producer = "Gary Kurtz", releaseDate = "1977-05-25",
		characters = emptyList(), planets = emptyList(), starships = emptyList(),
		vehicles = emptyList(), species = emptyList(), url = ""
	)
	val mockCharacter = Character(
		id = 1, name = "Luke Skywalker", height = "172", mass = "77", hairColor = "blond",
		skinColor = "fair", eyeColor = "blue", birthYear = "19BBY", gender = "male",
		homeworld = mockPlanet, films = listOf(mockFilm), species = emptyList(),
		vehicles = emptyList(), starships = emptyList(), url = ""
	)
	CharacterDetailContent(character = mockCharacter, onFilmClick = {})
}
