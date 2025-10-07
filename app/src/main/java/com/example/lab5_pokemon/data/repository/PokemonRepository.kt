package com.example.lab5_pokemon.data.repository

import com.example.lab5_pokemon.data.remote.RetrofitClient
import com.example.lab5_pokemon.domain.model.Pokemon

class PokemonRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getPokemonList(): Result<List<Pokemon>> {
        return try {
            val response = apiService.getPokemonList()
            val pokemonList = response.results.map { dto ->
                val id = dto.url.trimEnd('/').split('/').last()
                Pokemon(name = dto.name, id = id)
            }
            Result.success(pokemonList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}