package br.com.erolkss.projectpokemonventurus.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.erolkss.projectpokemonventurus.R
import br.com.erolkss.projectpokemonventurus.data.model.Result
import br.com.erolkss.projectpokemonventurus.databinding.ItemPokemonBinding
import com.bumptech.glide.Glide

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
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
    ): ViewHolder {
        val binding =
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val pokemon = list[position]
        holder.binding.pokemonName.text = pokemon.name.replaceFirstChar { it.uppercase() }

        // Extrai o ID do Pokémon pela URL
        val id = pokemon.url.split("/").dropLast(1).last()
        val imageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

        // Carrega imagem usando Glide com cache automático
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.background_circle_loading)
            .into(holder.binding.imagePokemon)
    }

    override fun getItemCount() = list.size

    fun submitList(newList: List<Result>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

}