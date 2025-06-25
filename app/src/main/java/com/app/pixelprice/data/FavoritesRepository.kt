package com.app.pixelprice.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FavoritesRepository : IFavoritesRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private fun getCurrentUserEmail(): String? {
        return auth.currentUser?.email
    }

    override suspend fun isFavorite(gameID: String): Boolean {
        val userEmail = getCurrentUserEmail() ?: return false

        return try {
            val doc = db.collection(userEmail).document(gameID).get().await()
            doc.exists()
        } catch (e: Exception) {
            Log.e("GAMEDB", "error checkeando si es favorito para gameID: $gameID: ${e.message}")
            false
        }
    }

    override suspend fun toggleFavorite(game: FavoriteGame): Boolean {
        val userEmail = getCurrentUserEmail()
            ?: throw IllegalStateException("El usuario debe estar logeado para marcar favoritos")

        val docRef = db.collection(userEmail).document(game.gameID)

        return try {
            val doc = docRef.get().await()

            if (doc.exists()) {
                docRef.delete().await()
                Log.d("GAMEDB", "Removido ${game.gameID} de favoritos")
                false
            } else {
                docRef.set(game).await()
                Log.d("GAMEDB", "agregado ${game.gameID} a favoritos")
                true
            }
        } catch (e: Exception) {
            Log.e("GAMEDB", "Error agregando a favoritos al gameID: ${game.gameID}: ${e.message}")
            throw e
        }
    }

    override suspend fun getFavoriteGames(): List<FavoriteGame> {
        val userEmail = getCurrentUserEmail()
            ?: throw IllegalStateException("el usuario debe estar logeado para ver sus favoritos")

        return try {
            val snapshot = db.collection(userEmail).get().await()

            val favorites = snapshot.toObjects(FavoriteGame::class.java)

            Log.d("GAMEDB", "se encontraron ${favorites.size} juegos favoritos")
            favorites
        } catch (e: Exception) {
            Log.e("GAMEDB", "error trayendo los favoritos: ${e.message}")
            throw e
        }
    }
}