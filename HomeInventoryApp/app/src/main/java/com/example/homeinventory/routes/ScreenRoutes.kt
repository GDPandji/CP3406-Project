package com.example.homeinventory.routes

val Screen.route: String
    get() = this.name.lowercase()