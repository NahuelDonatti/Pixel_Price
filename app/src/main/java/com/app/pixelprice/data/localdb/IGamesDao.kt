package com.app.pixelprice.data.localdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface IGamesDao {
    @Query("SELECT * FROM games")
    suspend fun getAll(): List<GameLocal>

    @Query("SELECT * FROM games WHERE gameID= :id LIMIT 1")
    suspend fun findById(id: String): GameLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg game: GameLocal)

    @Delete
    suspend fun delete(game: GameLocal)
}