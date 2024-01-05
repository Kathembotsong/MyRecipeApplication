package com.example.recipeapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.recipeapp.databinding.ActivityRecipeDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeDetailsBinding
    private lateinit var recipeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize ViewModel
        recipeViewModel = RecipeViewModel(application)
        var recipe: Recipe? = null
        // Example: Get recipe details based on ID from Intent
        val recipeId = intent.getLongExtra("recipe_item", 0)
        lifecycleScope.launch(Dispatchers.IO) {
            recipe = recipeViewModel.getRecipeById(recipeId)
            Log.d("In detail screen", "onCreate: recipe is += $recipe")
            binding.textViewTitle.text = "Title: " + recipe?.title
            binding.textViewMealType.text = "Mealtype: " + recipe?.mealType
            binding.textViewServingSize.text = "Serving size: " + recipe?.servingSize
            binding.difficulty.text = "Difficulty level: " + recipe?.difficultyLevel
            binding.textViewIngredients.text = "Ingregients: \n  \n" + recipe?.ingredients
            binding.textViewPreparationSteps.text = "Preparation steps: \n \n" + recipe?.preparationSteps
        }

    }
}
