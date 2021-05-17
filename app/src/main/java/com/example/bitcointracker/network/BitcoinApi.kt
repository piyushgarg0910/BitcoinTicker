package com.example.bitcointracker.network

import com.example.bitcointracker.model.BitcoinValueDTO
import retrofit2.http.GET

/**
 * This class defines the methods to interact with Bitcoin api endpoint
 *
 * @author Piyush Garg
 */
interface BitcoinApi {

    /**
     * This method is used to get the value of Bitcoin in all the currencies
     *
     * @return [BitcoinValueDTO] response for all currencies from the network
     */
    @GET ("ticker")
    suspend fun getBitcoinData() : BitcoinValueDTO
}