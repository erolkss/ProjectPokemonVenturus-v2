package br.com.erolkss.projectpokemonventurus.data.model

data class PokemonSpecies(
    val id: Int,
    val generation: Generation
)

data class Generation(
    val name: String
)