package br.com.erolkss.projectpokemonventurus.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.erolkss.projectpokemonventurus.R
import br.com.erolkss.projectpokemonventurus.data.api.ApiClient
import br.com.erolkss.projectpokemonventurus.data.model.Result
import br.com.erolkss.projectpokemonventurus.databinding.ActivityMainBinding
import br.com.erolkss.projectpokemonventurus.ui.detail.DetailPokemonActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = PokemonAdapter()
    private var allPokemonList: List<Result> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        adapter.onItemClick = { pokemon ->
            val intent = Intent(this, DetailPokemonActivity::class.java)
            intent.putExtra("pokemon_name", pokemon.name)
            startActivity(intent)
        }

        fetchPokemonList()
        setupSearchView()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun fetchPokemonList() {
        lifecycleScope.launch {
            val response = ApiClient.instance.getPokemonList()
            allPokemonList = response.results
            adapter.submitList(allPokemonList)
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText?.lowercase() ?: ""
                val filteredList = if (searchText.isEmpty()) {
                    allPokemonList
                } else {
                    allPokemonList.filter { pokemon ->
                        pokemon.name.lowercase().contains(searchText)
                    }
                }
                adapter.submitList(filteredList)
                return true
            }
        })
    }
}