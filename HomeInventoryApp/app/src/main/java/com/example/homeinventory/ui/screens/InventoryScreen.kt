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
fun InventoryScreen(viewModel: SharedInventoryViewModel = viewModel(), navController: NavController) {
    val items by viewModel.allItems.collectAsState(initial = emptyList())

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Inventory", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(items) { item ->
                ItemCard(
                    item = item,
                    onEditClick = { selectedItem ->
                        navController.navigate("${Screen.EditItem.name}/${selectedItem.id}")
                    },
                    onDeleteClick = { selectedItem ->
                        viewModel.deleteItem(selectedItem)
                    }
                )
            }
        }
    }
}


@Composable
fun ItemCard(item: Item, onEditClick: (Item) -> Unit, onDeleteClick: (Item) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onEditClick(item) }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Name: ${item.name}")
            Text("Description: ${item.description}")
            Text("Room: ${item.roomId}")
            Text("Category: ${item.category}")
            Text("Quantity: ${item.quantity}")

            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                TextButton(onClick = { onDeleteClick(item) }) {
                    Text("Delete", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}




