package com.example.fruitinformer.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitinformer.data.model.Fruit
import com.example.fruitinformer.data.repository.FruitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(
    application: Application,
    private val repository: FruitRepository
) : ViewModel() {

    private val _searchResult = MutableStateFlow<Fruit?>(null)
    val searchResult: StateFlow<Fruit?> = _searchResult.asStateFlow()

    private val _navigateToDetail = MutableStateFlow(false)
    val navigateToDetail: StateFlow<Boolean> = _navigateToDetail.asStateFlow()

    private val _favorites = MutableStateFlow<List<Fruit>>(emptyList())
    val favorites: StateFlow<List<Fruit>> = _favorites.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val sharedPreferences = application.getSharedPreferences("favorites_prefs", Context.MODE_PRIVATE)

    init {
        loadFavorites()
    }

    fun searchFruit(name: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val fruit = repository.getFruit(name)
                _searchResult.value = fruit
                _navigateToDetail.value = true
            } catch (e: Exception) {
                _searchResult.value = null
                _errorMessage.value = "Fruta não encontrada ou erro na conexão."
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun showFruitDetail(fruit: Fruit) {
        _searchResult.value = fruit
        _navigateToDetail.value = true
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = false
    }

    fun toggleFavorite(fruit: Fruit) {
        val currentFavorites = _favorites.value.toMutableList()
        if (currentFavorites.any { it.id == fruit.id }) {
            currentFavorites.removeAll { it.id == fruit.id }
        } else {
            currentFavorites.add(fruit)
        }
        _favorites.value = currentFavorites
        saveFavorites(currentFavorites)
    }

    private fun saveFavorites(fruits: List<Fruit>) {
        val editor = sharedPreferences.edit()
        val json = com.google.gson.Gson().toJson(fruits)
        editor.putString("favorite_fruits", json)
        editor.apply()
    }

    private fun loadFavorites() {
        val json = sharedPreferences.getString("favorite_fruits", null)
        if (json != null) {
            val type = object : com.google.gson.reflect.TypeToken<List<Fruit>>() {}.type
            val savedFavorites: List<Fruit> = com.google.gson.Gson().fromJson(json, type)
            _favorites.value = savedFavorites
        }
    }
}
