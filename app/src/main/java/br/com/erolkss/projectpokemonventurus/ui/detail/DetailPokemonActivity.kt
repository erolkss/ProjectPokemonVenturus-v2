package br.com.erolkss.projectpokemonventurus.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import br.com.erolkss.projectpokemonventurus.R
import br.com.erolkss.projectpokemonventurus.data.api.ApiClient
import br.com.erolkss.projectpokemonventurus.data.model.PokemonDetail
import br.com.erolkss.projectpokemonventurus.databinding.ActivityDetailPokemonBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class DetailPokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtnDetail.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val pokemonName = intent.getStringExtra("pokemon_name")

        if (!pokemonName.isNullOrEmpty()) {
            fetchPokemonDetails(pokemonName)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun fetchPokemonDetails(pokemonName: String) {
        lifecycleScope.launch {
            val response: PokemonDetail = ApiClient.instance.getPokemonByName(pokemonName)

            Glide.with(this@DetailPokemonActivity)
                .load(response.sprites.front_default)
                .into(binding.imagePokemon)

            binding.titleTxt.text = response.name.replaceFirstChar { it.uppercase() }
            binding.idTxt.text = getString(R.string.detail_id_format, response.id)
            binding.heightTxt.text = getString(
                R.string.detail_height_format,
                String.format("%.1f", response.height / 10.0)
            )
            binding.weightTxt.text = getString(
                R.string.detail_weight_format,
                String.format("%.1f", response.weight / 10.0)
            )


            val abilities = response.abilities.joinToString(", ") { it.ability.name }
            binding.abilitiesTxt.text = getString(R.string.detail_abilities_format, abilities)

            val types = response.types.joinToString(", ") { it.type.name }
            binding.typesTxt.text = getString(R.string.detail_types_format, types)

            val hp = response.stats.find { it.stat.name == "hp" }?.base_stat ?: 0
            val attack = response.stats.find { it.stat.name == "attack" }?.base_stat ?: 0
            val defense = response.stats.find { it.stat.name == "defense" }?.base_stat ?: 0

            binding.hpTxt.text = getString(R.string.detail_hp_format, hp)
            binding.attackTxt.text = getString(R.string.detail_attack_format, attack)
            binding.defenseTxt.text = getString(R.string.detail_defense_format, defense)
        }
    }
}