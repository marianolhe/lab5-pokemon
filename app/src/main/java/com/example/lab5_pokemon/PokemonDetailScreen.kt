package com.example.lab5_pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.lab5_pokemon.network.Pokemon
import java.util.Locale

@Composable
fun PokemonDetailScreen(name: String, id: String) {
    val pokemon = remember { Pokemon(name, id) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Nombre del Pokémon
        Text(
            text = name.replaceFirstChar { it.titlecase(Locale.getDefault()) },
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            textAlign = TextAlign.Center
        )

        // Primera fila: front y back
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberAsyncImagePainter(model = pokemon.imageUrlFront),
                    contentDescription = "Front",
                    modifier = Modifier.size(96.dp)
                )
                Text("Front", style = MaterialTheme.typography.bodyMedium)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberAsyncImagePainter(model = pokemon.imageUrlBack),
                    contentDescription = "Back",
                    modifier = Modifier.size(96.dp)
                )
                Text("Back", style = MaterialTheme.typography.bodyMedium)
            }
        }

        // Segunda fila: shiny front y shiny back
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberAsyncImagePainter(model = pokemon.imageUrlShinyFront),
                    contentDescription = "Shiny Front",
                    modifier = Modifier.size(96.dp)
                )
                Text("Shiny Front", style = MaterialTheme.typography.bodyMedium)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberAsyncImagePainter(model = pokemon.imageUrlShinyBack),
                    contentDescription = "Shiny Back",
                    modifier = Modifier.size(96.dp)
                )
                Text("Shiny Back", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
