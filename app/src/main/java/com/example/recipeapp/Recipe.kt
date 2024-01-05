package com.example.recipeapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "mealType")
    val mealType: String,
    @ColumnInfo(name = "servingSize")
    val servingSize: Int,
    @ColumnInfo(name = "difficultyLevel")
    val difficultyLevel: String,
    @ColumnInfo(name = "ingredients")
    val ingredients: String,
    @ColumnInfo(name = "preparationSteps")
    val preparationSteps: String
)
