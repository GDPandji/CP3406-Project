@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.homeinventory.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddItemScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Add Item", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Item Name") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Description") })
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /* Save logic */ }) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddItemScreenPreview() {
    AddItemScreen()
}