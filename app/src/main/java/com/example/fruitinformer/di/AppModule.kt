package com.example.fruitinformer.di

import com.example.fruitinformer.data.remote.FruityviceApi
import com.example.fruitinformer.data.repository.FruitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFruityviceApi(): FruityviceApi {
        return Retrofit.Builder()
            .baseUrl("https://www.fruityvice.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FruityviceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFruitRepository(api: FruityviceApi): FruitRepository {
        return FruitRepository(api)
    }
}
