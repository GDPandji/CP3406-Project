@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.homeinventory.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homeinventory.viewmodel.SharedInventoryViewModel
import com.example.homeinventory.model.RoomEntity

@Composable
fun RoomScreen(
    viewModel: SharedInventoryViewModel,
    onRoomSelected: (Int) -> Unit
) {
    val roomsWithItems by viewModel.roomsWithItems.collectAsState()
    var showAddRoomDialog by remember { mutableStateOf(false) }
    var showEditEmojiDialog by remember { mutableStateOf(false) }
    var newRoomName by remember { mutableStateOf("") }
    var selectedRoomForEdit by remember { mutableStateOf<RoomEntity?>(null) }
    var newEmoji by remember { mutableStateOf("🛋️") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Rooms", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(roomsWithItems.size) { index ->
                val room = roomsWithItems[index]
                RoomCard(
                    roomName = room.room.name,
                    itemCount = room.items.size,
                    emoji = room.room.icon,
                    onClick = { onRoomSelected(room.room.id) },
                    onEditClick = {
                        selectedRoomForEdit = room.room
                        newEmoji = room.room.icon
                        showEditEmojiDialog = true
                    }
                )
            }

            item {
                AddRoomCard {
                    showAddRoomDialog = true
                }
            }
        }
    }

    // Add Room Dialog
    if (showAddRoomDialog) {
        AlertDialog(
            onDismissRequest = { showAddRoomDialog = false },
            title = { Text("Add New Room") },
            text = {
                OutlinedTextField(
                    value = newRoomName,
                    onValueChange = { newRoomName = it },
                    label = { Text("Room Name") }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (newRoomName.isNotBlank()) {
                        viewModel.addRoom(newRoomName)
                        newRoomName = ""
                        showAddRoomDialog = false
                    }
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    newRoomName = ""
                    showAddRoomDialog = false
                }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Edit Emoji Dialog
    if (showEditEmojiDialog && selectedRoomForEdit != null) {
        AlertDialog(
            onDismissRequest = { showEditEmojiDialog = false },
            title = { Text("Change Room Emoji") },
            text = {
                OutlinedTextField(
                    value = newEmoji,
                    onValueChange = { newEmoji = it },
                    label = { Text("Emoji") },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    selectedRoomForEdit?.let { room ->
                        viewModel.updateRoomIcon(room.id, newEmoji)
                    }
                    showEditEmojiDialog = false
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showEditEmojiDialog = false
                }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun RoomCard(
    roomName: String,
    itemCount: Int,
    emoji: String,
    onClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(emoji, style = MaterialTheme.typography.headlineMedium)
            Text(roomName, style = MaterialTheme.typography.bodyLarge)
            Text("$itemCount item(s)", style = MaterialTheme.typography.bodySmall)
            Text(
                "✏️ Edit",
                modifier = Modifier.clickable { onEditClick() },
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun AddRoomCard(onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("➕", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Add Room", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

