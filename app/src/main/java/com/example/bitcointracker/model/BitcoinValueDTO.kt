package com.example.bitcointracker.model

import com.google.gson.annotations.SerializedName

/**
 * Data transfer object for capturing the Bitcoin api response from network
 *
 * @author Piyush Garg
 */
data class BitcoinValueDTO(
    @SerializedName("USD") val usdValue: CurrencyUsdDTO,
    @SerializedName("AUD") val audValue: CurrencyAudDTO,
    @SerializedName("BRL") val brlValue: CurrencyBrlDTO,
    @SerializedName("CAD") val cadValue: CurrencyCadDTO,
    @SerializedName("CLP") val clpValue: CurrencyClpDTO,
    @SerializedName("CNY") val cnyValue: CurrencyCnyDTO,
    @SerializedName("EUR") val eurValue: CurrencyEurDTO,
    @SerializedName("GBP") val gbpValue: CurrencyGbpDTO,
    @SerializedName("INR") val inrValue: CurrencyInrDTO,
    @SerializedName("KRW") val krwValue: CurrencyKrwDTO,
    @SerializedName("THB") val thbValue: CurrencyThbDTO,
    @SerializedName("TRY") val tryValue: CurrencyTryDTO
)