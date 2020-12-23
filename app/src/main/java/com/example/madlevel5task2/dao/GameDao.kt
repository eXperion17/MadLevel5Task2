package com.example.madlevel5task2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madlevel5task2.database.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM game_table ORDER BY releaseDate ASC")
    fun getGames(): LiveData<List<Game>>

    @Delete
    suspend fun deleteGame(game:Game)

    @Insert
    suspend fun insertGame(game:Game)

    @Query("DELETE FROM game_table")
    suspend fun deleteAllGames()
}