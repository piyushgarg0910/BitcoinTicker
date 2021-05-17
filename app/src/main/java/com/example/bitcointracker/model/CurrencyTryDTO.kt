package com.example.bitcointracker.model

import com.google.gson.annotations.SerializedName

/**
 * Data transfer object for TRY value of Bitcoin in api response
 * from the network
 *
 * @author Piyush Garg
 */
data class CurrencyTryDTO(
    @SerializedName("last") val last: Double,
    @SerializedName("symbol") val symbol: String)