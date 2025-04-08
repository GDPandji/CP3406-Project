@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.homeinventory.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(onAddItemClick: () -> Unit, onRoomClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home Inventory") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Welcome to Home Inventory App!")
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = onAddItemClick) {
                Text("Add Item")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRoomClick) {
                Text("View Rooms")
            }
        }
    }
}