package com.rj.models

import com.squareup.moshi.Json

//data class PawnItemResponse(@Json(name="results") val result : List<PawnItem>)
// response is returned inside pawnitems array
data class PawnItemResponse(
    @Json(name = "pawnitems")
    val result : List<PawnItem>
)