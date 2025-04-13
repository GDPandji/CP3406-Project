package com.example.homeinventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.homeinventory.R
import com.example.homeinventory.routes.Screen
import com.example.homeinventory.ui.HomeScreen
import com.example.homeinventory.ui.RoomScreen
import com.example.homeinventory.ui.screens.AddItemScreen
import com.example.homeinventory.ui.InventoryScreen
import com.example.homeinventory.viewmodel.SharedInventoryViewModel
import com.example.homeinventory.ui.EditItemScreen
import com.example.homeinventory.ui.screens.ItemDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeInventoryApp()
        }
    }
}

@Composable
fun HomeInventoryApp() {
    val navController = rememberNavController()
    val sharedViewModel: SharedInventoryViewModel = viewModel()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    // ✅ Inventory added to nav bar list
    val screens = listOf(Screen.Home, Screen.AddItem, Screen.Rooms, Screen.Inventory)

    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            when (screen) {
                                Screen.Home -> Icon(Icons.Default.Home, contentDescription = screen.title)
                                Screen.AddItem -> Icon(Icons.Default.List, contentDescription = screen.title)
                                Screen.Rooms -> Icon(
                                    painterResource(id = R.drawable.room),
                                    contentDescription = screen.title,
                                    modifier = Modifier.size(24.dp)
                                )
                                Screen.Inventory -> Icon(Icons.Default.Info, contentDescription = screen.title)
                                else -> Icon(Icons.Default.Settings, contentDescription = screen.title)
                            }
                        },
                        label = { Text(screen.title) },
                        selected = currentDestination == screen.name,
                        onClick = {
                            if (currentDestination != screen.name) {
                                navController.navigate(screen.name) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        inclusive = false
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.name) {
                HomeScreen(
                    navController = navController,
                    onAddItemClick = {
                        navController.navigate(Screen.AddItem.name)
                    },
                    onRoomClick = {
                        navController.navigate(Screen.Rooms.name)
                    },
                    viewModel = sharedViewModel
                )
            }
            composable(Screen.AddItem.name) {
                AddItemScreen(navController, viewModel = sharedViewModel)
            }
            composable(Screen.Rooms.name) {
                RoomScreen(
                    viewModel = sharedViewModel,
                    onRoomSelected = { roomId ->
                    navController.navigate("${Screen.ItemDetail.name}/$roomId")
                })
            }

            composable("${Screen.ItemDetail.name}/{itemId}") { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull()
                itemId?.let {
                    ItemDetailScreen(itemId = it, viewModel = sharedViewModel)
                }
            }

            composable("${Screen.EditItem.name}/{itemId}") { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull()
                itemId?.let {
                    EditItemScreen(itemId = it, viewModel = sharedViewModel, navController = navController)
                }
            }
            composable(Screen.Inventory.name) {
                InventoryScreen(viewModel = sharedViewModel, navController = navController)
            }
        }
    }
}
