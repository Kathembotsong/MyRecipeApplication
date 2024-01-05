package com.example.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.DetailsAdapterBinding

class RecipeAdapter(private var itemClickListener: ItemClickListener) : RecyclerView.Adapter<RecipeAdapter.NoteAdapterViewHolder>() {
    var allRecipes: List<Recipe> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapterViewHolder {
        val binding = DetailsAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
            return NoteAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteAdapterViewHolder, position: Int) {
        val current: Recipe = allRecipes.get(position)
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return allRecipes.size
    }

    fun setRecipes(notes: List<Recipe>) {
        this.allRecipes = notes
        notifyDataSetChanged()
    }

    inner class NoteAdapterViewHolder(private val binding: DetailsAdapterBinding)
        : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        init{
            binding.root.setOnClickListener(this)
        }
        fun bind(recipe: Recipe) {
            binding.txtTitle.text = recipe.title
            binding.txtMealType.text = recipe.mealType
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition
            when(p0){

                binding.root -> {
                    if (position != RecyclerView.NO_POSITION) {
                        itemClickListener.onItemClick(allRecipes, position)
                    }
                }
            }

        }
    }

    interface ItemClickListener {
        fun onItemClick(allRecipes: List<Recipe>,position: Int)
    }
}