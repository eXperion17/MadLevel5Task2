package com.example.madlevel5task2.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.madlevel5task2.database.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM game_table ORDER BY releaseDate ASC")
    fun getGames(): LiveData<Game>

    @Update
    suspend fun updateGame(note: Game)

    @Insert
    suspend fun insertGame(note: Game)
}