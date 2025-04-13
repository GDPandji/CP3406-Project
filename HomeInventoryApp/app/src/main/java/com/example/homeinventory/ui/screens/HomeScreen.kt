@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.homeinventory.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.homeinventory.model.Item
import com.example.homeinventory.routes.Screen
import com.example.homeinventory.viewmodel.SharedInventoryViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    onAddItemClick: () -> Unit,
    onRoomClick: () -> Unit,
    viewModel: SharedInventoryViewModel = viewModel()
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    val allItems by viewModel.allItems.collectAsState()
    val categories by viewModel.uniqueCategories.collectAsState()

    val filteredItems = allItems.filter {
        (searchText.text.isBlank() || it.name.contains(searchText.text, ignoreCase = true)) &&
                (selectedCategory == null || it.category == selectedCategory)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text("Home Inventory", fontSize = 24.sp, modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Search items...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = MaterialTheme.shapes.medium,
                singleLine = true
            )
        }

        item {
            Text("Categories", fontSize = 18.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            ) {
                // "All" category to clear filter
                CategoryChip(
                    label = "All",
                    selected = selectedCategory == null,
                    onClick = { selectedCategory = null }
                )

                Spacer(Modifier.width(8.dp))

                categories.forEach { category ->
                    CategoryChip(
                        label = category,
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category }
                    )
                    Spacer(Modifier.width(8.dp))
                }
            }
        }

        item {
            Text("Recent Items", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
        }

        items(filteredItems.take(5)) { item ->
            ItemRow(item = item, onClick = {
                navController.navigate("${Screen.ItemDetail.name}/${item.id}")
            })
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onAddItemClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Add Item")
            }

            Button(
                onClick = onRoomClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Rooms")
            }
        }
    }
}

@Composable
fun CategoryChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        shape = MaterialTheme.shapes.small,
        tonalElevation = if (selected) 4.dp else 0.dp,
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun ItemRow(item: Item, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(
            text = item.icon,
            fontSize = 24.sp,
            modifier = Modifier.padding(end = 12.dp)
        )
        Column {
            Text(item.name, fontSize = 16.sp)
            Text(item.category, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}




