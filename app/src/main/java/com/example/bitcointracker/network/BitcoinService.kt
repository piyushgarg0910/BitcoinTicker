package com.example.bitcointracker.network

import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class initializes retrofit for communicating with Bitcoin Ticker
 * api endpoint
 *
 * @author Piyush Garg
 */
class BitcoinService {

    private val retrofit = Builder()
        .baseUrl("https://blockchain.info/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: BitcoinApi = retrofit.create(BitcoinApi::class.java)
}