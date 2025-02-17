package com.example.homepage.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.homepage.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var selectedTab by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // App Bar
        Text(
            text = "Home Inventory",
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )

        // Search Bar
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("Search items...") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,
                disabledContainerColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Category Section
        Text("Categories", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            CategoryItem("Tools")
            CategoryItem("Kitchen")
            CategoryItem("Documents")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Recent Items Section
        Text("Recent Items", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
        Column {
            ItemRow("Hammer", R.drawable.hammer)
            ItemRow("Recipe Book", R.drawable.recipe_book)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation Bar
        NavigationBar(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
    }
}

@Composable
fun CategoryItem(name: String) {
    Card(
        modifier = Modifier.padding(8.dp).size(100.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(name, fontSize = 14.sp)
        }
    }
}

@Composable
fun ItemRow(itemName: String, imageRes: Int) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = itemName,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(itemName, fontSize = 16.sp, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun NavigationBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Inventory") },
            label = { Text("Inventory") },
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.room), contentDescription = "Rooms", modifier = Modifier.size(24.dp)) },
            label = { Text("Rooms") },
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}

