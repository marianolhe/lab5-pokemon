package com.example.lab5_pokemon.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5_pokemon.data.repository.PokemonRepository
import com.example.lab5_pokemon.domain.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PokemonListUiState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class PokemonListViewModel : ViewModel() {
    private val repository = PokemonRepository()

    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()

    init {
        loadPokemonList()
    }

    private fun loadPokemonList() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            repository.getPokemonList()
                .onSuccess { pokemonList ->
                    _uiState.value = _uiState.value.copy(
                        pokemonList = pokemonList,
                        isLoading = false
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Error: ${exception.message}"
                    )
                }
        }
    }

    fun retry() {
        loadPokemonList()
    }
}