package com.example.lab5_pokemon.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://pokeapi.co/api/v2/"

// Modelo de cada Pokémon en la respuesta
data class PokemonResult(
    val name: String,
    val url: String
)

// Nuevo nombre para la respuesta de la API
data class PokemonListResponse(
    val results: List<PokemonResult>
)

// Interfaz Retrofit: define la llamada a la API
interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): PokemonListResponse
}

// Objeto singleton para acceder al servicio Retrofit desde cualquier parte de la app
object PokeApi {
    val retrofitService: PokeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }
}
