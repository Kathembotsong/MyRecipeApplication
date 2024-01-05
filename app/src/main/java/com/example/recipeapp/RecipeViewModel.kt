package com.example.recipeapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers

class RecipeViewModel(
    application: Application
) : AndroidViewModel(application) {
    private var repository: RecipeRepository
    var allRecipes: LiveData<List<Recipe>> // Use LiveData for observation

    init {
        val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(recipeDao)
        allRecipes = repository.allRecipes
    }

    fun insert(recipe: Recipe) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(recipe)
    }
    suspend fun getAllRecipes(): LiveData<List<Recipe>> {
        return repository.getAll()
    }

     fun getRecipeById(recipeId: Long): Recipe {
        return repository.getRecipeById(recipeId)
    }
}
