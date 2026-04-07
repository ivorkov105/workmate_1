package test_tasks.workmate_test_task.data.datasources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starships")
data class StarshipEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val model: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val maxAtmospheringSpeed: String,
    val crew: String,
    val passengers: String,
    val cargoCapacity: String,
    val consumables: String,
    val hyperdriveRating: String,
    val MGLT: String,
    val starshipClass: String,
    val created: String,
    val edited: String,
    val url: String
)
