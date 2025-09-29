package com.example.lab5_pokemon.network

// Modelo de respuesta de la API
data class PokeResponse(
    val results: List<PokemonApi>
)

// Cada Pokémon que viene de la API
data class PokemonApi(
    val name: String,
    val url: String
)

// Clase modelo de Pokémon en la app
data class Pokemon(
    val name: String,
    val id: String
) {
    // URL para la imagen frontal normal
    val imageUrlFront: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

    // URL para la imagen trasera normal
    val imageUrlBack: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/$id.png"

    // URL para la imagen frontal shiny
    val imageUrlShinyFront: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png"

    // URL para la imagen trasera shiny
    val imageUrlShinyBack: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/$id.png"
}
