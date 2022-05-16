package com.example.mobilszoftverlabormovies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobilszoftverlabormovies.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}