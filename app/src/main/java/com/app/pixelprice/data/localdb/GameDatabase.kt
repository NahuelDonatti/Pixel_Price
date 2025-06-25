package com.app.pixelprice.data.localdb

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Database(entities = [GameLocal::class], version=1)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gamesDao(): IGamesDao

    companion object{
        @Volatile
        private var _instance: GameDatabase? = null

        fun getInstance(context: Context): GameDatabase = _instance ?: synchronized(this) {
            _instance ?: buildDatabase(context)
        }


        private fun buildDatabase(context: Context): GameDatabase = Room.databaseBuilder(context,
            GameDatabase::class.java, "game_database").fallbackToDestructiveMigration().build()

        suspend fun clean(context: Context) = coroutineScope {
            launch(Dispatchers.IO) {
                getInstance(context).clearAllTables()
            }
        }
    }
}