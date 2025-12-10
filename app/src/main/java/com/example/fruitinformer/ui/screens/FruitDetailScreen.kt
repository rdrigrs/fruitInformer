package com.example.fruitinformer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fruitinformer.ui.viewmodel.FruitViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitDetailScreen(
    viewModel: FruitViewModel,
    navController: NavController
) {
    val fruit by viewModel.searchResult.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    val isFavorite = fruit?.let { f -> favorites.any { it.id == f.id } } ?: false

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(fruit?.name ?: "Fruit Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    fruit?.let {
                        IconButton(onClick = { viewModel.toggleFavorite(it) }) {
                            Icon(
                                if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            fruit?.let {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(text = "Name: ${it.name}", style = MaterialTheme.typography.headlineMedium)
                    Text(text = "Family: ${it.family}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Genus: ${it.genus}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Order: ${it.order}", style = MaterialTheme.typography.bodyLarge)
                    
                    Divider()
                    
                    Text(text = "Nutritions (per 100g):", style = MaterialTheme.typography.titleLarge)
                    Text(text = "Calories: ${it.nutritions.calories}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Carbohydrates: ${it.nutritions.carbohydrates}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Protein: ${it.nutritions.protein}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Fat: ${it.nutritions.fat}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Sugar: ${it.nutritions.sugar}", style = MaterialTheme.typography.bodyMedium)
                }
            } ?: run {
                Text("No fruit details available.", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
