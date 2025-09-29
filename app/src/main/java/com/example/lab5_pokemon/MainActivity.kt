package com.example.lab5_pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab5_pokemon.ui.theme.Lab5pokemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab5pokemonTheme {
                Surface(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PokemonApp() // Aquí iniciaremos nuestra app con navegación
                }
            }
        }
    }
}

@Composable
fun PokemonApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "pokemon_list") {
        composable("pokemon_list") {
            PokemonListScreen(navController)
        }
        composable("pokemon_detail/{name}/{id}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            val id = backStackEntry.arguments?.getString("id")
            if (name != null && id != null) {
                PokemonDetailScreen(name, id)
            }
        }
    }
}

