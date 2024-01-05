package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.databinding.ActivityExploreBinding
import kotlinx.coroutines.launch

class ExploreActivity : AppCompatActivity() {

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var adapter: RecipeAdapter // Create a RecyclerView Adapter
    private lateinit var binding: ActivityExploreBinding
    private var items: MutableList<Recipe> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recipeViewModel = RecipeViewModel(requireNotNull(this).application)

        adapter = RecipeAdapter(object : RecipeAdapter.ItemClickListener {
            override fun onItemClick(recipes: List<Recipe>, position: Int) {

                val intent = Intent(Intent(
                    this@ExploreActivity,
                    RecipeDetailsActivity::class.java
                ))
                val bundle = Bundle()
                bundle.putLong("recipe_item", recipes[position].id) // "ITEM_ID" is the key to retrieve the ID in the next activity
                intent.putExtras(bundle)
                startActivity(intent)
            }

        })
        // Observe changes in the ViewModel's LiveData
        recipeViewModel.allRecipes.observe(this) { recipes ->
            // Log.d("Explore ativity observe", "onCreate: recipes is $recipes")
            items = recipes.toMutableList()
            // Update the adapter with the new list of recipe
            adapter.setRecipes(recipes)//
        }
        insertItems()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    fun insertItems() {
        val demoItems = listOf<Recipe>(
            Recipe(
                title = "Pasta Carbonara",
                mealType = "Dinner",
                servingSize = 2,
                difficultyLevel = "Medium",
                ingredients = "Pasta \n Eggs \n Bacon \n Parmesan Cheese \n Black Pepper",
                preparationSteps = "Boil pasta until al dente. Cook bacon until crispy. Whisk eggs and cheese. Combine all ingredients."
            ),
            Recipe(
                title = "Chicken Stir-Fry",
                mealType = "Lunch",
                servingSize = 4,
                difficultyLevel = "Easy",
                ingredients = "Chicken \n Bell Peppers \n Onion \n Soy Sauce \n Rice",
                preparationSteps = "Stir-fry chicken until cooked. Add vegetables and soy sauce. Serve over rice."
            ),
            Recipe(
                title = "Vegetable Curry",
                mealType = "Dinner",
                servingSize = 3,
                difficultyLevel = "Medium",
                ingredients = "Vegetables \n Coconut Milk \n Curry Paste \n Rice",
                preparationSteps = "Simmer vegetables in coconut milk and curry paste. Serve over rice."
            ),
            Recipe(
                title = "Tacos",
                mealType = "Dinner",
                servingSize = 4,
                difficultyLevel = "Easy",
                ingredients = "Tortillas \n Ground Beef \n Lettuce \n Tomatoes \n Cheese",
                preparationSteps = "Cook ground beef, add taco seasoning. Assemble tacos with ingredients."
            ),
            Recipe(
                title = "Mushroom Risotto",
                mealType = "Dinner",
                servingSize = 3,
                difficultyLevel = "Hard",
                ingredients = "Arborio Rice \n Mushrooms \n Onion \n Vegetable Broth \n Parmesan Cheese",
                preparationSteps = "Sauté onions and mushrooms. Cook rice in vegetable broth. Stir in parmesan cheese."
            )
        )

        lifecycleScope.launch {
            demoItems.map {
                recipeViewModel.insert(it)
            }
        }
    }
}
