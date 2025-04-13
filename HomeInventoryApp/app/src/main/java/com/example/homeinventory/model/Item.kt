package com.example.homeinventory.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class RoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val icon: String = "🛋️" // default emoji if none provided
)

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val roomId: Int,
    val quantity: Int = 1,
    val category: String = "",
    val imageUri: String? = null,
    val icon: String = "🔧" // default emoji
)

data class RoomWithItems(
    @Embedded val room: RoomEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "roomId"  // ✅ matches field name in Item
    )
    val items: List<Item>
)

