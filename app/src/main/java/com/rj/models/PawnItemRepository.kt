package com.rj.models

import com.rj.network.PawnItemApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PawnItemRepository @Inject constructor(private val pawnItemApi: PawnItemApi) {
    suspend fun fetchPawnItems() = pawnItemApi.fetchPawnItems()
    // This isn't an optimal implementation because it doesn't take into
    // account caching. We'll look at how to improve upon this
}