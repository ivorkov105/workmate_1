package test_tasks.workmate_test_task.data.datasources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "species")
data class SpeciesEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val classification: String,
    val designation: String,
    val averageHeight: String,
    val skinColors: String,
    val hairColors: String,
    val eyeColors: String,
    val averageLifespan: String,
    val homeworldId: Int?,
    val language: String,
    val created: String,
    val edited: String,
    val url: String
)
