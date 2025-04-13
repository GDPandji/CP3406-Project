@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.homeinventory.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.homeinventory.routes.Screen
import com.example.homeinventory.util.ReminderScheduler
import com.example.homeinventory.viewmodel.SharedInventoryViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddItemScreen(
    navController: NavController,
    viewModel: SharedInventoryViewModel
) {
    val context = LocalContext.current

    val itemName by viewModel.itemName.collectAsState()
    val itemDescription by viewModel.itemDescription.collectAsState()
    val itemRoomId by viewModel.itemRoomId.collectAsState()
    val allRooms by viewModel.allRooms.collectAsState()
    val itemQuantity by viewModel.itemQuantity.collectAsState()
    val itemCategory by viewModel.itemCategory.collectAsState()
    val itemIcon by viewModel.itemIcon.collectAsState()

    var expanded by remember { mutableStateOf(false) }
    var showAddRoomDialog by remember { mutableStateOf(false) }
    var newRoomName by remember { mutableStateOf("") }

    val selectedRoomName = allRooms.firstOrNull { it.id == itemRoomId }?.name ?: "Select Room"

    // Reminder and Image picker
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri.value = it
    }

    val calendar = remember { Calendar.getInstance() }
    val selectedDateTime = remember { mutableStateOf<Long?>(null) }

    // Emoji picker
    val emojiOptions = listOf(
        "🔧", "🪑", "🛏️", "🧹", "🍽️", "📦", "📚", "🖼️", "🎒", "🧥",
        "🧺", "🧼", "🖥️", "🕯️", "🪞", "🧯", "🚪", "🚽", "🪠", "🛁",
        "🪒", "🪜", "🧻", "📺", "📷", "🎮", "🕹️", "🔋", "🔌", "🧰",
        "🔑", "🛠️", "🧲", "💡", "🔦", "🧪", "🧴", "🧽", "🪣", "🪤",
        "🧊", "🪙", "🪡", "🧵", "🖊️", "📐", "🪛", "🧯", "🔨", "🪚"
    )
    var emojiMenuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Add Item", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = itemName,
            onValueChange = { viewModel.updateItemName(it) },
            label = { Text("Item Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = itemDescription,
            onValueChange = { viewModel.updateItemDescription(it) },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Quantity", style = MaterialTheme.typography.bodyMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { viewModel.decreaseQuantity() }, enabled = itemQuantity > 1) { Text("-") }
            Text(text = itemQuantity.toString(), modifier = Modifier.padding(horizontal = 16.dp))
            Button(onClick = { viewModel.increaseQuantity() }) { Text("+") }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = itemCategory,
            onValueChange = { viewModel.updateItemCategory(it) },
            label = { Text("Category / Type") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Room", style = MaterialTheme.typography.bodySmall)
        Box(modifier = Modifier.fillMaxWidth().clickable { expanded = true }.padding(8.dp)) {
            Text(text = selectedRoomName)
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            allRooms.forEach { room ->
                DropdownMenuItem(
                    text = { Text(room.name) },
                    onClick = {
                        viewModel.updateItemRoomId(room.id)
                        expanded = false
                    }
                )
            }
            Divider()
            DropdownMenuItem(
                text = { Text("➕ Add New Room") },
                onClick = {
                    expanded = false
                    showAddRoomDialog = true
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Choose Emoji Icon: $itemIcon", modifier = Modifier.clickable { emojiMenuExpanded = true })

        DropdownMenu(expanded = emojiMenuExpanded, onDismissRequest = { emojiMenuExpanded = false }) {
            emojiOptions.forEach { emoji ->
                DropdownMenuItem(
                    text = { Text(emoji) },
                    onClick = {
                        viewModel.updateItemIcon(emoji)
                        emojiMenuExpanded = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { imagePicker.launch("image/*") }) {
            Text("Select Image")
        }

        imageUri.value?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Item Image",
                modifier = Modifier.size(200.dp).padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            val now = Calendar.getInstance()
            DatePickerDialog(context, { _, y, m, d ->
                TimePickerDialog(context, { _, h, min ->
                    calendar.set(y, m, d, h, min, 0)
                    selectedDateTime.value = calendar.timeInMillis
                }, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false).show()
            }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).show()
        }) {
            Text("Set Reminder Date")
        }

        selectedDateTime.value?.let {
            Text("Reminder: ${SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date(it))}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        val isValid = itemName.isNotBlank() && itemDescription.isNotBlank() &&
                itemRoomId != 0 && itemCategory.isNotBlank()

        Button(
            onClick = {
                val uriString = imageUri.value?.toString()
                viewModel.addItem(
                    name = itemName,
                    description = itemDescription,
                    roomId = itemRoomId,
                    quantity = itemQuantity,
                    category = itemCategory,
                    imageUri = uriString
                )

                selectedDateTime.value?.let { millis ->
                    ReminderScheduler.scheduleReminder(
                        context = context,
                        triggerTimeMillis = millis,
                        title = "Reminder: $itemName",
                        message = "Time to reorganize or retrieve it!"
                    )
                }

                navController.navigate(Screen.Home.name)
            },
            enabled = isValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate(Screen.Home.name) {
                    popUpTo(Screen.Home.name) { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }

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
                    viewModel.addRoom(newRoomName)
                    newRoomName = ""
                    showAddRoomDialog = false
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
}

