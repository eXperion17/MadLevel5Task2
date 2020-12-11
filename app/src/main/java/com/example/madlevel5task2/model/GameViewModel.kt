package com.example.madlevel5task1.model

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

class NoteViewModel(application: Application) : AndroidViewModel(application) {
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

        if (isNoteValid(newGame)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.updateGame(newGame)
                }
                success.value = true
            }
        }
    }

    private fun isNoteValid(game: Game): Boolean {
        return when {
            game.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            } else -> true
        }
    }


}
