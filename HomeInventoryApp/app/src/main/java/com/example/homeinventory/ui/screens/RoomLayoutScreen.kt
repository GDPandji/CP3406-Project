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
    val allRooms by viewModel.allRooms.collectAsState()
    val placedItems = allItems.filter { it.roomId == roomId && it.gridX != null && it.gridY != null }
    val unplacedItems = allItems.filter { it.roomId == roomId && (it.gridX == null || it.gridY == null) }
    val selectedItemId by viewModel.selectedItemId.collectAsState()
    var showDetailItem by remember { mutableStateOf<Item?>(null) }

    val room = allRooms.find { it.id == roomId }
    val rows = room?.gridRows ?: 6
    val cols = room?.gridCols ?: 6

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
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
            rows = rows,
            cols = cols,
            placedItems = placedItems,
            selectedItemId = selectedItemId,
            onCellClicked = { x, y ->
                selectedItemId?.let { itemId ->
                    scope.launch {
                        viewModel.updateItemPosition(itemId, x, y)
                        viewModel.clearSelectedItem()
                    }
                }
            },
            onItemClicked = { item ->
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
    onCellClicked: (x: Int, y: Int) -> Unit,
    onItemClicked: (Item) -> Unit
) {
    val maxGridWidth = 300.dp
    val maxGridHeight = 300.dp

    val cellWidth = remember(cols) {
        (maxGridWidth / cols.toFloat()).coerceAtMost(60.dp)
    }
    val cellHeight = remember(rows) {
        (maxGridHeight / rows.toFloat()).coerceAtMost(60.dp)
    }

    val grid = Array(rows) { arrayOfNulls<Item>(cols) }

    for (item in placedItems) {
        val startX = item.gridX ?: continue
        val startY = item.gridY ?: continue
        val width = item.width.coerceAtLeast(1)
        val height = item.height.coerceAtLeast(1)

        for (dy in 0 until height) {
            for (dx in 0 until width) {
                val x = startX + dx
                val y = startY + dy
                if (x in 0 until cols && y in 0 until rows) {
                    grid[y][x] = item
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .widthIn(max = maxGridWidth)
            .heightIn(max = maxGridHeight)
            .background(Color.Transparent)
    ) {
        for (y in 0 until rows) {
            Row {
                for (x in 0 until cols) {
                    val item = grid[y][x]
                    val isTopLeft = item?.gridX == x && item.gridY == y

                    Box(
                        modifier = Modifier
                            .width(cellWidth)
                            .height(cellHeight)
                            .padding(1.dp)
                            .background(if (item != null) Color(0xFFD0F0C0) else Color.LightGray)
                            .clickable {
                                when {
                                    item != null && isTopLeft -> onItemClicked(item)
                                    item == null && selectedItemId != null -> onCellClicked(x, y)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (item != null) {
                            Text(
                                item.icon ?: "📦",
                                color = if (isTopLeft) LocalContentColor.current else Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}



