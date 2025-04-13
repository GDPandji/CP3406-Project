@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.homeinventory.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.homeinventory.viewmodel.SharedInventoryViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ItemDetailScreen(itemId: Int, viewModel: SharedInventoryViewModel) {
    val itemState = remember { mutableStateOf<com.example.homeinventory.model.Item?>(null) }

    // Fetch item using viewModel
    LaunchedEffect(itemId) {
        viewModel.getItemById(itemId).collectLatest { item ->
            itemState.value = item
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Item Details") })
        }
    ) { padding ->
        val item = itemState.value
        if (item != null) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("${item.icon} ${item.name}", style = MaterialTheme.typography.titleMedium)
                Text("Description: ${item.description}")
                Text("Quantity: ${item.quantity}")
                Text("Category: ${item.category}")
                Text("Room ID: ${item.roomId}")
                Spacer(modifier = Modifier.height(16.dp))

                item.imageUri?.let {
                    Image(
                        painter = rememberAsyncImagePainter(it),
                        contentDescription = "Item Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

