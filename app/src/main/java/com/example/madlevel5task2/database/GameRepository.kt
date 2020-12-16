package com.example.madlevel5task2.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madlevel5task2.dao.GameDao

class GameRepository (context: Context){
    private val gameDao:GameDao
    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getGameList(): LiveData<Game> {
        return gameDao.getGames()
    }

    suspend fun updateGame(note:Game) {
        gameDao.updateGame(note)
    }
}