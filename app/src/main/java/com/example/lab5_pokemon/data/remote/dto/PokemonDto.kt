package com.example.lab5_pokemon.data.remote.dto

data class PokemonListResponse(
    val results: List<PokemonResultDto>
)

data class PokemonResultDto(
    val name: String,
    val url: String
)