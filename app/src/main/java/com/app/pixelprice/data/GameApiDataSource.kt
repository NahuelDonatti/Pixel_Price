package com.app.pixelprice.data

import android.util.Log
import com.app.pixelprice.data.localdb.GameDatabase
import com.app.pixelprice.data.localdb.GameDatabaseProvider
import com.app.pixelprice.data.localdb.toExternal
import com.app.pixelprice.data.localdb.toLocal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import okio.IOException
import retrofit2.HttpException

class GameApiDataSource() : IGameDataSource {

    override suspend fun getGameList(search: String): List<Game> {
        try {
            val gameResult = RetrofitInstance.gameAPI.getGameSearch(search)
            return gameResult
        } catch (e: HttpException) {
            Log.e("GameApiDataSource", "Error de HTTP: ${e.message}", e)
            return emptyList()
        } catch (e: IOException) {
            Log.e("GameApiDataSource", "Error de internet: ${e.message}", e)
            return emptyList()
        } catch (e: Exception) {
            Log.e("GameApiDataSource", "Error recuperando la lista de juegos: ${e.message}", e)
            return emptyList()
        }
    }

    override suspend fun getDealByID(dealID: String): GameDetailsResponse {

        val db= FirebaseFirestore.getInstance()
        val dbLocal = GameDatabaseProvider.dbLocal

        try {
            Log.d("GAMEDB", "Verificando base de datos local")
            val allGames = dbLocal.gamesDao().getAll()
            Log.d("GAMEDB", "Cantidad de juegos en la DB: ${allGames.size}")
        } catch (e: Exception) {
            Log.e("GAMEDB", "Error verificando base de datos local: ${e.message}")
            e.printStackTrace()
        }

        try {
            Log.d("GAMEDB", "dealID original: $dealID")
            val safeDealID = dealID.toFirestoreSafeId()
            Log.d("GAMEDB", "Intentando buscar juego local con safe id: $safeDealID")
            val gameLocal = dbLocal.gamesDao().findById(safeDealID)
            if (gameLocal != null) {
                Log.d("GAMEDB", "Juego encontrado en bd local: $gameLocal")
                val gameLocal = gameLocal.toExternal()
                Log.d("GAMEDB", "e: $gameLocal")
                return gameLocal
            } else {
                Log.d("GAMEDB", "Juego no encontrado en DB local")
            }
        } catch (e: Exception) {
            Log.e("GAMEDB", "Error revisando la bd local: ${e.message}")
            e.printStackTrace()
        }

        val safeDealID = dealID.toFirestoreSafeId()

        try {


            var gameResult = db.collection("Favoritos").document(safeDealID).get().await()
            var game = gameResult.toObject(GameDetailsResponse::class.java)

            if (game != null){
                Log.d("GameDB", "Encontrado en firestore")
                Log.d("GameDB", "Intentando guardar de manera local")
                val gameLocal = game.gameInfo.toLocal()
                Log.d("GAMEDB", "creado juego local: $gameLocal id: ${gameLocal.gameID}")

                Log.d("GAMEDB", "instancia DAO")
                val dao = dbLocal.gamesDao()
                Log.d("GAMEDB", "intentando insertar localmente")
                dao.insert(gameLocal)
                Log.d("GAMEDB", "guardado localmente")
                return game
            }

            else{
                Log.d("GAMEDB", "no encontrado Firestore")
            }
        } catch (e: Exception) {
                Log.e("GAMEDB", "Error solicitando datos de Firestore: ${e.message}", e)
        }

        try {
            Log.d("GAMEDB", "Trayendo desde la API")
            val game = RetrofitInstance.gameAPI.getDealByID(dealID)
            try{
                db.collection("Favoritos").document(safeDealID).set(game).await()
            } catch (e: Exception){
                Log.e("GAMEDB", "Error guardando en Firestore: ${e.message}", e)
            }

            try{
                val gameLocal = game.gameInfo.toLocal()
                dbLocal.gamesDao().insert(gameLocal)
            } catch (e: Exception){
                Log.e("GAMEDB", "Error guardando localmente: ${e.message}", e)
            }
            return game
        } catch (e: Exception) {
            Log.e("GAMEDB", "Error trayendo desde la api: ${e.message}", e)
            throw e
        }
        return emptyGame()
}
}

