package com.example.homeinventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homeinventory.routes.Screen
import com.example.homeinventory.ui.HomeScreen

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
    MaterialTheme {
        NavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) { HomeScreen(navController) }
        }
    }
}