package com.rj.network

import com.rj.models.PawnItemResponse
import retrofit2.http.GET

interface PawnItemApi {
    //Call url without parameters. "exec" exists in the actual url endpoint at the end i.e, www.abc.com/api/exec/
    @GET("macros/s/AKfycbzuohncR-i_yP33VLRMm8UDLVLArkp3zoD8U1G2kH-UXEayU1epwI3hcXZmuQy0DYsC/exec")
    suspend fun fetchPawnItems(): PawnItemResponse
}