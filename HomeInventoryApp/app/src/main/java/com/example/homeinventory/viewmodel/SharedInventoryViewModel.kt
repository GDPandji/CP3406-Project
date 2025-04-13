package com.example.homeinventory.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeinventory.data.AppDatabase
import com.example.homeinventory.model.Item
import com.example.homeinventory.model.RoomEntity
import com.example.homeinventory.model.RoomWithItems
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SharedInventoryViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val itemDao = db.itemDao()
    private val roomDao = db.roomDao()

    // Items
    private val _allItems = itemDao.getAllItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val allItems: StateFlow<List<Item>> = _allItems

    val uniqueCategories: StateFlow<List<String>> = allItems
        .map { items -> items.map { it.category }.distinct() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    // Rooms
    private val _allRooms = roomDao.getAllRooms()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val allRooms: StateFlow<List<RoomEntity>> = _allRooms

    // Rooms with items
    private val _roomsWithItems = roomDao.getRoomsWithItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val roomsWithItems: StateFlow<List<RoomWithItems>> = _roomsWithItems

    // Item form state
    private val _itemName = MutableStateFlow("")
    val itemName: StateFlow<String> = _itemName

    private val _itemDescription = MutableStateFlow("")
    val itemDescription: StateFlow<String> = _itemDescription

    private val _itemRoomId = MutableStateFlow(0)
    val itemRoomId: StateFlow<Int> = _itemRoomId

    private val _itemQuantity = MutableStateFlow(1)
    val itemQuantity: StateFlow<Int> = _itemQuantity

    private val _itemCategory = MutableStateFlow("")
    val itemCategory: StateFlow<String> = _itemCategory

    private val _itemImageUri = MutableStateFlow<String?>(null)
    val itemImageUri: StateFlow<String?> = _itemImageUri

    private val _itemIcon = MutableStateFlow("🔧") // default item emoji
    val itemIcon: StateFlow<String> = _itemIcon

    // Room form state
    private val _roomIcon = MutableStateFlow("🛋️") // default room emoji
    val roomIcon: StateFlow<String> = _roomIcon

    // ======================
    // Item Field Updaters
    // ======================

    fun updateItemName(newName: String) {
        _itemName.value = newName
    }

    fun updateItemDescription(newDescription: String) {
        _itemDescription.value = newDescription
    }

    fun updateItemRoomId(newRoomId: Int) {
        _itemRoomId.value = newRoomId
    }

    fun updateItemCategory(newCategory: String) {
        _itemCategory.value = newCategory
    }

    fun updateItemImageUri(newUri: String?) {
        _itemImageUri.value = newUri
    }

    fun updateItemIcon(newIcon: String) {
        _itemIcon.value = newIcon
    }

    fun increaseQuantity() {
        _itemQuantity.value += 1
    }

    fun decreaseQuantity() {
        if (_itemQuantity.value > 1) {
            _itemQuantity.value -= 1
        }
    }

    // ======================
    // Room Field Updaters
    // ======================

    fun updateRoomIcon(newIcon: String) {
        _roomIcon.value = newIcon
    }

    // ======================
    // Add / Update Entities
    // ======================

    fun addItem(
        name: String,
        description: String,
        roomId: Int,
        quantity: Int,
        category: String,
        imageUri: String?
    ) {
        if (roomId == 0) return // Invalid room
        viewModelScope.launch {
            itemDao.insert(
                Item(
                    name = name,
                    description = description,
                    roomId = roomId,
                    quantity = quantity,
                    category = category,
                    imageUri = imageUri,
                    icon = _itemIcon.value // <- item emoji
                )
            )
            clearItemFields()
        }
    }

    fun updateItem(updatedItem: Item) {
        viewModelScope.launch {
            itemDao.update(updatedItem)
        }
    }

    fun getItemById(id: Int): Flow<Item?> {
        return itemDao.getItemById(id)
    }

    fun addRoom(name: String) {
        viewModelScope.launch {
            roomDao.insert(RoomEntity(name = name, icon = _roomIcon.value))
            _roomIcon.value = "🛋️" // reset
        }
    }

    fun updateRoomIcon(roomId: Int, newIcon: String) {
        viewModelScope.launch {
            val currentRoom = allRooms.value.find { it.id == roomId }
            if (currentRoom != null) {
                val updatedRoom = currentRoom.copy(icon = newIcon)
                roomDao.update(updatedRoom)
            }
        }
    }

    // ======================
    // Utilities
    // ======================

    private fun clearItemFields() {
        _itemName.value = ""
        _itemDescription.value = ""
        _itemRoomId.value = 0
        _itemQuantity.value = 1
        _itemCategory.value = ""
        _itemImageUri.value = null
        _itemIcon.value = "🔧"
    }
}



