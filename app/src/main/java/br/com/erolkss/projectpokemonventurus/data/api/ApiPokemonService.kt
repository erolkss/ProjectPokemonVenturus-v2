package br.com.erolkss.projectpokemonventurus.data.api

import br.com.erolkss.projectpokemonventurus.data.model.PokemonDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon?limit=100")
    suspend fun getPokemonList(): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonDetail

}