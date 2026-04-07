package test_tasks.workmate_test_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import test_tasks.workmate_test_task.presentation.StarWarsNav
import test_tasks.workmate_test_task.ui.theme.Workmate_1Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Workmate_1Theme {
                StarWarsNav()
            }
        }
    }
}
