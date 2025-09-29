package com.example.lab5_pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.lab5_pokemon.network.Pokemon
import com.example.lab5_pokemon.network.PokeApi
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun PokemonListScreen(navController: NavHostController) {
    var pokemonList by remember { mutableStateOf<List<Pokemon>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = PokeApi.retrofitService.getPokemonList()
                pokemonList = response.results.map { poke ->
                    val id = poke.url.trimEnd('/').split('/').last()
                    Pokemon(name = poke.name, id = id)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Pokémon List",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp)
            )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(pokemonList) { pokemon ->
                PokemonCard(pokemon, navController)
            }
        }
    }
}

@Composable
fun PokemonCard(pokemon: Pokemon, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("pokemon_detail/${pokemon.name}/${pokemon.id}")
            }
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(pokemon.imageUrlFront),
                contentDescription = pokemon.name,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = pokemon.name.replaceFirstChar { it.titlecase(Locale.getDefault()) },
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
