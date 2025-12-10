package com.example.fruitinformer.data.repository

import com.example.fruitinformer.data.model.Fruit
import com.example.fruitinformer.data.remote.FruityviceApi

class FruitRepository(private val api: FruityviceApi) {
    suspend fun getFruit(name: String): Fruit {
        return api.getFruitByName(name)
    }

    suspend fun getAllFruits(): List<Fruit> {
        return api.getAllFruits()
    }
}
