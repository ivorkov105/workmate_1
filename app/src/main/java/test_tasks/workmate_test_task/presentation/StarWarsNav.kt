package test_tasks.workmate_test_task.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import test_tasks.workmate_test_task.presentation.characters.CharactersScreen
import test_tasks.workmate_test_task.presentation.characters.CharactersViewModel
import test_tasks.workmate_test_task.presentation.detail.CharacterDetailScreen
import test_tasks.workmate_test_task.presentation.detail.CharacterDetailViewModel

sealed class Screen(val route: String) {
    object Characters : Screen("characters")
    object CharacterDetail : Screen("character_detail/{characterId}") {
        fun createRoute(id: Int) = "character_detail/$id"
    }
}

@Composable
fun StarWarsNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Characters.route
    ) {
        composable(Screen.Characters.route) {
            val viewModel: CharactersViewModel = hiltViewModel()
            CharactersScreen(
                viewModel = viewModel,
                onNavigateToDetail = { id ->
                    navController.navigate(Screen.CharacterDetail.createRoute(id))
                }
            )
        }
        composable(
            route = Screen.CharacterDetail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) {
            val viewModel: CharacterDetailViewModel = hiltViewModel()
            CharacterDetailScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
