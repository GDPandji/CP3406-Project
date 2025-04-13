@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.homeinventory.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.homeinventory.viewmodel.SharedInventoryViewModel

@Composable
fun EditItemScreen(
    itemId: Int,
    viewModel: SharedInventoryViewModel = viewModel(),
    navController: NavController
) {
    val item = viewModel.getItemById(itemId).collectAsState(initial = null).value

    item?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Edit Item", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))

            var name by remember { mutableStateOf(it.name) }
            var description by remember { mutableStateOf(it.description) }
            var category by remember { mutableStateOf(it.category ?: "") }
            var quantity by remember { mutableStateOf(it.quantity ?: 1) }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { if (quantity > 1) quantity-- }) {
                    Text("-")
                }
                Text(" Qty: $quantity ", modifier = Modifier.padding(horizontal = 8.dp))
                Button(onClick = { quantity++ }) {
                    Text("+")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                viewModel.updateItem(it.copy(name = name, description = description, category = category, quantity = quantity))
            }) {
                Text("Save")
            }
        }
    } ?: run {
        Text("Item not found", modifier = Modifier.padding(16.dp))
    }
}
