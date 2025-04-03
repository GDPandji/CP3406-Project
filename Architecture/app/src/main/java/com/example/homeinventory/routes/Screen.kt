package com.example.homeinventory.routes

sealed class Screen(val route: String) {
    object Home : Screen("home")
}