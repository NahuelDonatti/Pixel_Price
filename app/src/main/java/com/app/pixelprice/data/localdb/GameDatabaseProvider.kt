package com.app.pixelprice.data.localdb

import android.content.Context

object GameDatabaseProvider {
    lateinit var dbLocal: GameDatabase
        private set

    fun createDatabase(context: Context){
        dbLocal = GameDatabase.getInstance(context)
    }
}