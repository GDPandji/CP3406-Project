package com.example.homeinventory.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItemById(id: Int): Flow<Item?>

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun update(item: Item)
}

@Dao
interface RoomDao {
    @Query("SELECT * FROM RoomEntity")
    fun getAllRooms(): Flow<List<RoomEntity>>

    @Insert
    suspend fun insert(room: RoomEntity)

    @Transaction
    @Query("SELECT * FROM RoomEntity")
    fun getRoomsWithItems(): Flow<List<RoomWithItems>>

    @Update
    suspend fun update(room: RoomEntity)

    @Query("SELECT * FROM items WHERE id = :itemId LIMIT 1")
    suspend fun getItemByIdOnce(itemId: Int): Item?

}
