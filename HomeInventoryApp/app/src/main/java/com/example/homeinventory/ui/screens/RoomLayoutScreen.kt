@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.homeinventory.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.homeinventory.model.Item
import com.example.homeinventory.viewmodel.SharedInventoryViewModel
import kotlinx.coroutines.launch

@Composable
fun RoomLayoutScreen(
    roomId: Int,
    viewModel: SharedInventoryViewModel
) {
    val allItems by viewModel.allItems.collectAsState()
    val placedItems = allItems.filter { it.roomId == roomId && it.gridX != null && it.gridY != null }
    val unplacedItems = allItems.filter { it.roomId == roomId && (it.gridX == null || it.gridY == null) }
    val selectedItemId by viewModel.selectedItemId.collectAsState()

    var showDetailItem by remember { mutableStateOf<Item?>(null) }

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Room Layout", style = MaterialTheme.typography.headlineSmall)
            Button(onClick = {
                scope.launch {
                    placedItems.forEach { item ->
                        viewModel.updateItemPosition(item.id, null, null)
                    }
                }
            }) {
                Text("Clear Grid")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        GridLayout(
            rows = 6,
            cols = 6,
            placedItems = placedItems,
            selectedItemId = selectedItemId,
            onDrop = { itemId, x, y ->
                scope.launch {
                    viewModel.updateItemPosition(itemId, x, y)
                    viewModel.clearSelectedItem()
                }
            },
            onPlacedItemClick = { item ->
                showDetailItem = item
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text("Unplaced Items", style = MaterialTheme.typography.titleMedium)

        LazyColumn {
            items(unplacedItems) { item ->
                ListItem(
                    headlineContent = { Text(item.name) },
                    leadingContent = { Text(item.icon ?: "📦") },
                    modifier = Modifier.clickable {
                        viewModel.selectItemForPlacement(item.id)
                    }
                )
            }
        }
    }

    // Item detail dialog
    showDetailItem?.let { item ->
        AlertDialog(
            onDismissRequest = { showDetailItem = null },
            title = { Text("Item Details") },
            text = {
                Column {
                    Text("Name: ${item.name}")
                    Text("Description: ${item.description}")
                    Text("Category: ${item.category}")
                    Text("Quantity: ${item.quantity}")
                    Text("Icon: ${item.icon ?: "📦"}")
                    Text("Size: ${item.width} x ${item.height}")
                }
            },
            confirmButton = {
                TextButton(onClick = { showDetailItem = null }) {
                    Text("Close")
                }
            }
        )
    }
}

@Composable
fun GridLayout(
    rows: Int,
    cols: Int,
    placedItems: List<Item>,
    selectedItemId: Int?,
    onDrop: (itemId: Int, x: Int, y: Int) -> Unit,
    onPlacedItemClick: (Item) -> Unit
) {
    val cellSize = 60.dp
    val occupiedMap = mutableMapOf<Pair<Int, Int>, Item>()

    // Track occupied cells
    for (item in placedItems) {
        val x = item.gridX ?: continue
        val y = item.gridY ?: continue
        for (dx in 0 until item.width) {
            for (dy in 0 until item.height) {
                occupiedMap[Pair(x + dx, y + dy)] = item
            }
        }
    }

    Column {
        for (y in 0 until rows) {
            Row {
                for (x in 0 until cols) {
                    val position = Pair(x, y)
                    val item = occupiedMap[position]

                    Box(
                        modifier = Modifier
                            .size(cellSize)
                            .padding(4.dp)
                            .background(Color.LightGray)
                            .clickable {
                                if (selectedItemId != null && item == null) {
                                    onDrop(selectedItemId, x, y)
                                } else if (item != null) {
                                    onPlacedItemClick(item)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(item?.icon ?: "")
                    }
                }
            }
        }
    }
}



