package com.example.fruitinformer.data.model

data class Fruit(
    val id: Int,
    val name: String,
    val genus: String,
    val family: String,
    val order: String,
    val nutritions: Nutritions
)

data class Nutritions(
    val carbohydrates: Double,
    val protein: Double,
    val fat: Double,
    val calories: Int,
    val sugar: Double
)
