package br.com.erolkss.projectpokemonventurus.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.erolkss.projectpokemonventurus.R
import br.com.erolkss.projectpokemonventurus.data.model.Result
import br.com.erolkss.projectpokemonventurus.databinding.ItemPokemonBinding
import com.bumptech.glide.Glide

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private val list = mutableListOf<Result>()
    var onItemClick: ((Result) -> Unit)? = null

    inner class ViewHolder(val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(list[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PokemonViewHolder,
        position: Int
    ) {
        val pokemon = list[position]
        holder.bind(pokemon)
    }

    override fun getItemCount() = list.size

    fun submitList(newList: List<Result>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imagePokemon: ImageView = itemView.findViewById(R.id.imagePokemon)
        private val pokemonName: TextView = itemView.findViewById(R.id.pokemonName)
        private val generationPokemon: TextView = itemView.findViewById(R.id.generationPokemon)

        fun bind(pokemon: Result) {
            pokemonName.text = pokemon.name.replaceFirstChar { it.uppercase() }

            generationPokemon.text = pokemon.generation ?: "Unknown Generation"

            val id = pokemon.url.split("/").dropLast(1).last()

            val imageUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
            Glide.with(itemView.context)
                .load(imageUrl)
                .into(imagePokemon)

            itemView.setOnClickListener { onItemClick?.invoke(pokemon) }
        }
    }
}