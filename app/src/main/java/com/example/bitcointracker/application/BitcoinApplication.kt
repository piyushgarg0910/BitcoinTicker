package com.example.bitcointracker.application

import android.app.Application
import com.example.bitcointracker.network.BitcoinService

/**
 * This is the application class used for initializing services that will be consumed
 * throughout the application lifecycle
 *
 * @author Piyush Garg
 */
class BitcoinApplication : Application() {

    companion object {
        /**
         * [bitcoinApi] is an instance of retrofit that can be used anywhere in the
         * application to make network calls
         */
        val bitcoinApi = BitcoinService().api
    }
}