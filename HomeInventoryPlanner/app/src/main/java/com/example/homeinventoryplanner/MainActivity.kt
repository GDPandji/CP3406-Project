package com.example.homeinventoryplanner

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
import androidx.navigation.compose.rememberNavController
import com.example.homeinventoryplanner.ui.theme.HomeInventoryPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeInventoryPlannerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val items = remember { mutableStateListOf("Toy Box", "Kitchen Supplies", "Documents") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Home Inventory", style = MaterialTheme.typography.headlineMedium)

        LazyColumn {
            items(items) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {},
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Text(text = item, modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text("Add Item")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeInventoryPlannerTheme {
        HomeScreen()
    }
}
