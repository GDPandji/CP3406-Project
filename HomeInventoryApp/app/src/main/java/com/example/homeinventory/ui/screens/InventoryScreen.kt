package com.example.homeinventory.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.homeinventory.viewmodel.SharedInventoryViewModel
import com.example.homeinventory.model.Item
import com.example.homeinventory.routes.Screen

@Composable
fun InventoryScreen(
    viewModel: SharedInventoryViewModel = viewModel(),
    navController: NavController
) {
    val items by viewModel.allItems.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Inventory", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(items) { item ->
            ItemCard(item = item, onEditClick = { selectedItem ->
                navController.navigate("${Screen.EditItem.name}/${selectedItem.id}")
            })
        }
    }
}

@Composable
fun ItemCard(item: Item, onEditClick: (Item) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEditClick(item) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "${item.icon ?: "📦"} ${item.name}", style = MaterialTheme.typography.titleMedium)
            Text("Description: ${item.description}")
            Text("Room ID: ${item.roomId}")
            Text("Category: ${item.category}")
            Text("Quantity: ${item.quantity}")
        }
    }
}



