package test_tasks.workmate_test_task.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import test_tasks.workmate_test_task.domain.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel,
    onBackClick: () -> Unit
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
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF5C6BC0))
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
                CharacterDetailContent(state.character, Modifier.padding(padding))
            }
            is CharacterDetailUiState.Error -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(state.message, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Composable
fun CharacterDetailContent(character: Character, modifier: Modifier = Modifier) {
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

        item { SectionTitle("Species") }
        if (character.species.isEmpty()) {
            item { EmptyInfoCard("No species information available") }
        } else {
            items(character.species) { species ->
                InfoCard("Classification", species.classification)
            }
        }

        item { SectionTitle("Films") }
        items(character.films) { film ->
            FilmCard(film.title, film.openingCrawl)
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
fun InfoCard(label: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAF6))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = label, fontSize = 14.sp, color = Color.Gray)
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun EmptyInfoCard(message: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAF6))
    ) {
        Box(Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
            Text(text = message, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun FilmCard(title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAF6))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3949AB))
            Spacer(Modifier.height(4.dp))
            Text(text = description, fontSize = 14.sp, color = Color.DarkGray, maxLines = 3)
        }
    }
}
