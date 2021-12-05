package com.rj.network

import com.rj.models.PawnItemResponse
import com.rj.util.Util
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
//import retrofit2.http.Query
//import retrofit2.Call

object ApiClient {
    private val moshi = Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Util.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    //create instance for ApiService interface
    val apiService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    //Call url without parameters. "exec" exists in the actual url at the end i., www.abc.com/api/exec/
    @GET("macros/s/AKfycbzuohncR-i_yP33VLRMm8UDLVLArkp3zoD8U1G2kH-UXEayU1epwI3hcXZmuQy0DYsC/exec")
    suspend fun fetchPawnItems(): PawnItemResponse

/*
    // www.app.net/api/searchtypes/862189/filters?Type=6&SearchText=School
    // For the above urlNow this is the call:
    @GET("/api/searchtypes/{Id}/filters")
    Call<FilterResponse> getFilterList(
              @Path("Id") long customerId, //862189
              @Query("Type") String responseType,
              @Query("SearchText") String searchText
    )
*/

}