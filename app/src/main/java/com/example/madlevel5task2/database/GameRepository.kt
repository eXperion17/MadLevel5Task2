package com.example.madlevel5task2.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task2.dao.GameDao

class GameRepository (context: Context){
    private val gameDao:GameDao
    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getGameList(): LiveData<List<Game>> {
        return gameDao.getGames()
    }

    suspend fun insertGame(game:Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteGame(game:Game) {
        gameDao.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

}