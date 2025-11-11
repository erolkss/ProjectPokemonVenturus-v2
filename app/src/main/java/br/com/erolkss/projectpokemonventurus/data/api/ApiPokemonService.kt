package br.com.erolkss.projectpokemonventurus.data.api

import br.com.erolkss.projectpokemonventurus.data.model.PokemonDetail
import br.com.erolkss.projectpokemonventurus.data.model.PokemonSpecies
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon?limit=300")
    suspend fun getPokemonList(): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonDetail

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(@Path("name") name: String): PokemonSpecies

}