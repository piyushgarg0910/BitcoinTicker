package com.example.bitcointracker.model

import com.google.gson.annotations.SerializedName

/**
 * Data transfer object for AUD value of Bitcoin in api response
 * from the network
 *
 * @author Piyush Garg
 */
data class CurrencyAudDTO(
    @SerializedName("last") val last: Double,
    @SerializedName("symbol") val symbol: String)
