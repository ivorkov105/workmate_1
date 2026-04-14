package test_tasks.workmate_test_task.presentation.details.film

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.unit.sp
import test_tasks.workmate_test_task.R
import test_tasks.workmate_test_task.domain.model.Film
import test_tasks.workmate_test_task.ui.components.ClickableCharacterCard
import test_tasks.workmate_test_task.ui.components.ErrorScreen
import test_tasks.workmate_test_task.ui.components.InfoCard
import test_tasks.workmate_test_task.ui.components.SectionTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailScreen(
    viewModel: FilmDetailViewModel,
    onBackClick: () -> Unit,
    onCharacterClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    val title = (uiState as? FilmDetailUiState.Success)?.film?.title ?: ""
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
            is FilmDetailUiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFF5C6BC0))
                }
            }
            is FilmDetailUiState.Success -> {
                FilmDetailContent(
                    film = state.film,
                    onCharacterClick = onCharacterClick,
                    modifier = Modifier.padding(padding)
                )
            }
            is FilmDetailUiState.Error -> {
                ErrorScreen(
                    message = state.message,
                    onRetry = { /* В реальном приложении здесь вызываем метод viewModel.retry() */ }
                )
            }
        }
    }
}

@Composable
fun FilmDetailContent(
    film: Film,
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item { SectionTitle("Film Information") }
        item { InfoCard("Episode", film.episodeId.toString()) }
        item { InfoCard("Director", film.director) }
        item { InfoCard("Producer", film.producer) }
        item { InfoCard("Release Date", film.releaseDate) }

        item { SectionTitle("Opening Crawl") }
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAF6))
            ) {
                Text(
                    text = film.openingCrawl,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }

        if (film.characters.isNotEmpty()) {
            item { SectionTitle("Characters") }
            items(film.characters) { character ->
                ClickableCharacterCard(character.name) { onCharacterClick(character.id) }
            }
        }

        if (film.planets.isNotEmpty()) {
            item { SectionTitle("Planets") }
            items(film.planets) { planet ->
                InfoCard("Planet", planet.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilmDetailContentPreview() {
    val mockFilm = Film(
        id = 1,
        title = "A New Hope",
        episodeId = 4,
        openingCrawl = "It is a period of civil war. Rebel spaceships, striking from a hidden base, have won their first victory against the evil Galactic Empire.",
        director = "George Lucas",
        producer = "Gary Kurtz, Rick McCallum",
        releaseDate = "1977-05-25",
        characters = emptyList(),
        planets = emptyList(),
        starships = emptyList(),
        vehicles = emptyList(),
        species = emptyList(),
        url = ""
    )
    FilmDetailContent(film = mockFilm, onCharacterClick = {})
}
