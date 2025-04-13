@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.homeinventory.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.homeinventory.routes.Screen
import com.example.homeinventory.viewmodel.SharedInventoryViewModel
import androidx.compose.ui.text.input.TextFieldValue
import com.example.homeinventory.model.Item

@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    onAddItemClick: () -> Unit,
    onRoomClick: () -> Unit,
    viewModel: SharedInventoryViewModel = viewModel()
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val allItems by viewModel.allItems.collectAsState()
    val categories by viewModel.uniqueCategories.collectAsState()


    val filteredItems = allItems.filter {
        it.name.contains(searchText.text, ignoreCase = true) ||
                it.category.contains(searchText.text, ignoreCase = true)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

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

        Spacer(modifier = Modifier.height(16.dp))

        Text("Categories", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            categories.forEach { category ->
                CategoryItem(name = category, onClick = { /* Optional: filter or navigate */ })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Recent Items", fontSize = 18.sp, modifier = Modifier.padding(8.dp))

        LazyColumn {
            items(filteredItems.take(5)) { item ->
                ItemRow(item)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

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

@Composable
fun CategoryItem(name: String, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(name, fontSize = 14.sp)
        }
    }
}

@Composable
fun ItemRow(item: Item) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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



