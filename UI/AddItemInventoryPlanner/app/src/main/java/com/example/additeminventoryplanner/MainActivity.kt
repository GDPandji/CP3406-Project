package com.example.additeminventoryplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.additeminventoryplanner.ui.theme.AddItemInventoryPlannerTheme

class AddItemInventoryPlanner : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AddItemInventoryPlannerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AddItemScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AddItemScreen(modifier: Modifier = Modifier) {
    var itemName by remember { mutableStateOf("") }
    var itemCategory by remember { mutableStateOf("") }
    var itemLocation by remember { mutableStateOf("") }
    val itemDimensions by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Add New Item", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item Name") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        OutlinedTextField(
            value = itemCategory,
            onValueChange = { itemCategory = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        OutlinedTextField(
            value = itemLocation,
            onValueChange = { itemLocation = it },
            label = { Text("Storage Location") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        OutlinedTextField(
            value = itemDimensions,
            onValueChange = { itemLocation = it },
            label = { Text("Item Dimensions") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text("Save Item")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddItemScreenPreview() {
    AddItemInventoryPlannerTheme {
        AddItemScreen()
    }
}
