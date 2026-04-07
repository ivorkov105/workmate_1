package test_tasks.workmate_test_task.data.datasources.remote.models

import com.google.gson.annotations.SerializedName

data class SpeciesDto(
    val name: String,
    val classification: String,
    val designation: String,
    @SerializedName("average_height") val averageHeight: String,
    @SerializedName("skin_colors") val skinColors: String,
    @SerializedName("hair_colors") val hairColors: String,
    @SerializedName("eye_colors") val eyeColors: String,
    @SerializedName("average_lifespan") val averageLifespan: String,
    val homeworld: String?,
    val language: String,
    val people: List<String>,
    val films: List<String>,
    val created: String,
    val edited: String,
    val url: String
)
