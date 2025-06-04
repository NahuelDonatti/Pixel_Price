package com.app.pixelprice.data


interface IStoreRepository {
    suspend fun fetchStores(): List<Stores>

    suspend fun getStoresMap(): Map<String, Stores>

    suspend fun getStoreById(storeID: String): Stores?
}
