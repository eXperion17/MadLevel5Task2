package com.example.madlevel5task2.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task2.database.Game
import com.example.madlevel5task2.database.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val gameRepository =  GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val games = gameRepository.getGameList()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun insertGame(game:Game) {
        mainScope.launch {
            val addedGame = withContext(Dispatchers.IO) {
                //Add game to database!
                gameRepository.insertGame(game);
            }
            success.value = true
        }
    }

    fun deleteGame(game:Game) {
        mainScope.launch {
            val addedGame = withContext(Dispatchers.IO) {
                gameRepository.deleteGame(game);
            }
            success.value = true
        }
    }


    fun getAllGames() : LiveData<List<Game>> {
        return gameRepository.getGameList();
    }

    private fun isGameValid(game: Game): Boolean {
        return when {
            game.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            } else -> true
        }
    }


}
