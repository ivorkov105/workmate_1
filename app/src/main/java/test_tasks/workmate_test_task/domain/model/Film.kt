package test_tasks.workmate_test_task.domain.model

data class Film(
    val id: Int,
    val title: String,
    val episodeId: Int,
    val openingCrawl: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val characters: List<Character>,
    val planets: List<Planet>,
    val starships: List<Starship>,
    val vehicles: List<Vehicle>,
    val species: List<Species>,
    val url: String
)
