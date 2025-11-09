package br.com.erolkss.projectpokemonventurus.data.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val abilities: List<AbilitySlot>,
    val types: List<TypeSlot>,
    val stats: List<StatSlot>,
)

data class Sprites(
    val front_default: String
)

data class AbilitySlot(
    val ability: Ability
)

data class Ability(
    val name: String
)

data class TypeSlot(
    val type: Type
)

data class Type(
    val name: String
)

data class StatSlot(
    val base_stat: Int,
    val stat: Stat
)

data class Stat(
    val name: String
)