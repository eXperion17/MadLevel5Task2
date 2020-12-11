package com.example.madlevel5task2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "game_table")
data class Game (
    @ColumnInfo(name = "game")
    var title: String,
    @ColumnInfo(name = "releaseDate")
    var releaseDate: Date,
    @ColumnInfo(name = "platform")
    var platform: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long? = null
)