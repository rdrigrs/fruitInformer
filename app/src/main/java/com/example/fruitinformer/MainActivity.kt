package com.example.fruitinformer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fruitinformer.data.remote.RetrofitInstance
import com.example.fruitinformer.data.repository.FruitRepository
import com.example.fruitinformer.ui.screens.FavoritesScreen
import com.example.fruitinformer.ui.screens.FruitDetailScreen
import com.example.fruitinformer.ui.screens.SearchScreen
import com.example.fruitinformer.ui.theme.FruitInformerTheme
import com.example.fruitinformer.ui.viewmodel.FruitViewModel
import com.example.fruitinformer.ui.viewmodel.FruitViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = FruitRepository(RetrofitInstance.api)
        val viewModelFactory = FruitViewModelFactory(application, repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[FruitViewModel::class.java]

        setContent {
            FruitInformerTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "search") {
                    composable("search") {
                        SearchScreen(viewModel = viewModel, navController = navController)
                    }
                    composable("fruit_detail") {
                        FruitDetailScreen(viewModel = viewModel, navController = navController)
                    }
                    composable("favorites") {
                        FavoritesScreen(viewModel = viewModel, navController = navController)
                    }
                }
            }
        }
    }
}
