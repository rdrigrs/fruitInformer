package com.example.fruitinformer.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fruitinformer.ui.viewmodel.FruitViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FruitViewModel,
    navController: NavController
) {
    val favorites by viewModel.favorites.collectAsState()
    val navigateToDetail by viewModel.navigateToDetail.collectAsState()

    // Handle navigation
    LaunchedEffect(navigateToDetail) {
        if (navigateToDetail) {
            navController.navigate("fruit_detail")
            viewModel.onDetailNavigated()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorites") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (favorites.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No favorite fruits yet.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(favorites) { fruit ->
                    ListItem(
                        headlineContent = { Text(fruit.name) },
                        supportingContent = { Text(fruit.family) },
                        modifier = Modifier.clickable {
                            viewModel.showFruitDetail(fruit)
                        }
                    )
                    Divider()
                }
            }
        }
    }
}
