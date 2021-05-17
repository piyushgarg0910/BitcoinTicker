package com.example.bitcointracker.viewModel.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointracker.view.activity.BitcoinActivity.Companion.bitcoinRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

/**
 * ViewModel class for the BitcoinActivity
 *
 * @author Piyush Garg
 */
class BitcoinActivityViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * A ticker to run the data fetch from network every second
     */
    @OptIn(ObsoleteCoroutinesApi::class)
    private var tickerChannel: ReceiveChannel<Unit> =
        ticker(1000, 0, Dispatchers.IO)

    /**
     * This method starts the Bitcoin data fetch from the network
     * it in the database when it receives the ticker event (every second)
     */
    fun updateBitcoinValues() {
        viewModelScope.launch {
            for (event in tickerChannel) {
                bitcoinRepo.updateBitcoinData()
            }
        }
    }

    /**
     * Handles resetting the viewModel elements
     */
    fun destroy() {
        tickerChannel.cancel()
    }
}