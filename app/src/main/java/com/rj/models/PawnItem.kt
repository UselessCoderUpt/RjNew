package com.rj.models

import com.squareup.moshi.Json

data class PawnItem(
    @Json(name="ID")
    val LoanNo: Int,
    @Json(name="Name")
    val CustomerName: String,
    @Json(name="Mobile number")
    val MobileNo: Long,
    @Json(name="Place")
    val Place: String,
    @Json(name="Type")
    val ItemType: String,
    @Json(name="Item")
    val ItemName: String,
    @Json(name="Weight")
    val Weight: Double,
    @Json(name="Amount")
    val Amount: Double,
    @Json(name="Renew INT")
    val RenewInterest: Double,
    @Json(name="TOTAL RENEW")
    val TotalAmount: Double
    )

