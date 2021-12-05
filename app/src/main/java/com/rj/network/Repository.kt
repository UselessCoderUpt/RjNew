package com.rj.network

class Repository(private val apiService: ApiService) {
    //TODO: Need to use suspend function
    suspend fun fetchPawnItems() = apiService.fetchPawnItems()
    // This isn't an optimal implementation because it doesn't take into
    // account caching. We'll look at how to improve upon this
}