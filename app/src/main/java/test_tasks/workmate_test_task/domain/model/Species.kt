package test_tasks.workmate_test_task.domain.model

data class Species(
    val id: Int,
    val name: String,
    val classification: String,
    val designation: String,
    val averageHeight: String,
    val skinColors: String,
    val hairColors: String,
    val eyeColors: String,
    val averageLifespan: String,
    val homeworld: String?,
    val language: String,
    val url: String
)
