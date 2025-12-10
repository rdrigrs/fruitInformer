package com.example.fruitinformer.data.remote

import com.example.fruitinformer.data.model.Fruit
import retrofit2.http.GET
import retrofit2.http.Path

interface FruityviceApi {
    @GET("api/fruit/all")
    suspend fun getAllFruits(): List<Fruit>

    @GET("api/fruit/{name}")
    suspend fun getFruitByName(@Path("name") name: String): Fruit
}
