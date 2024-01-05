package com.example.recipeapp

import androidx.lifecycle.LiveData

class RecipeRepository(private val recipeDao: RecipeDao) {
    val allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipes()

    suspend fun insert(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }
     fun getAll(): LiveData<List<Recipe>> {
       return recipeDao.getAllRecipes()
    }

    fun getRecipeById(recipeId: Long): Recipe {
        return recipeDao.getRecipeById(recipeId)
    }

}
