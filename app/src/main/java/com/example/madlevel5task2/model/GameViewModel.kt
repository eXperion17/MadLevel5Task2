package com.example.madlevel5task2.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    val game = gameRepository.getGameList()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun updateGame(title:String, releaseDate:String, platform:String) {
        val newGame = Game(
            id = game.value?.id,
            title = title,
            //TODO: FIX DATE
            releaseDate = Date(),
            platform = platform
        )

        if (isGameValid(newGame)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.updateGame(newGame)
                }
                success.value = true
            }
        }
    }

    fun insertGame(game:Game) {
        mainScope.launch {
            val addedGame = withContext(Dispatchers.IO) {
                //Add game to database!
                gameRepository.insertGame(game);
            }
            success.value = true
        }
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
