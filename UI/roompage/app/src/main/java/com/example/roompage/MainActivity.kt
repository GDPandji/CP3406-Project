package com.example.roompage.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roompage.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Place

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
    var selectedTab by remember { mutableStateOf(2) } // Default to RoomPage for preview

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        when (selectedTab) {
            0 -> HomeContent()
            1 -> InventoryContent()
            2 -> RoomPage()
            3 -> SettingsContent()
        }

        Spacer(modifier = Modifier.weight(1f))
        NavigationBar(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
    }

}

@Composable
fun HomeContent() {
    Text("Welcome to Home Inventory", fontSize = 24.sp, modifier = Modifier.padding(8.dp))
}

@Composable
fun InventoryContent() {
    Text("Inventory List", fontSize = 24.sp, modifier = Modifier.padding(8.dp))
}

@Composable
fun SettingsContent() {
    Text("Settings", fontSize = 24.sp, modifier = Modifier.padding(8.dp))
}

@Composable
fun RoomPage() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Room Layout", fontSize = 24.sp, modifier = Modifier.padding(8.dp))

        Canvas(modifier = Modifier.size(300.dp).padding(16.dp)) {
            // Draw the room walls
            drawRect(color = Color.Gray, size = size, style = Stroke(width = 5f))

            // Draw furniture as rectangles
            drawRect(color = Color.DarkGray, topLeft = Offset(50f, 50f), size = androidx.compose.ui.geometry.Size(80f, 50f)) // Bed
            drawRect(color = Color.LightGray, topLeft = Offset(180f, 100f), size = androidx.compose.ui.geometry.Size(50f, 30f)) // Table
            drawRect(color = Color.Black, topLeft = Offset(100f, 200f), size = androidx.compose.ui.geometry.Size(60f, 40f)) // Chair
        }
    }
}

@Composable
fun NavigationBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
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
            icon = { Icon(Icons.Filled.Place, contentDescription = "Rooms") },
            label = { Text("Rooms") },
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = selectedTab == 3,
            onClick = { onTabSelected(3) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoomPageWithNavBar() {
    Column(modifier = Modifier.fillMaxSize()) {
        RoomPage()
        Spacer(modifier = Modifier.weight(1f))
        NavigationBar(selectedTab = 2, onTabSelected = {})
    }
}


