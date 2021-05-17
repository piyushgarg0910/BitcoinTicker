package com.example.bitcointracker.viewModel.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointracker.model.BitcoinValueModel
import com.example.bitcointracker.util.SingleLiveEvent
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
class BitcoinFragmentViewModel(application: Application): AndroidViewModel(application) {

    /**
     * [bitcoinValueUpdated] is a Single Live Event for triggering the UI
     * to load the data that is fetched from the database
     */
    val bitcoinValueUpdated = SingleLiveEvent<List<BitcoinValueModel>>()

    /**
     * A ticker to run the data fetch from database every second with an initial delay
     * of 2 seconds
     */
    @OptIn(ObsoleteCoroutinesApi::class)
    private var tickerChannel: ReceiveChannel<Unit> =
        ticker(1000, 2000, Dispatchers.IO)

    /**
     * This method starts the Bitcoin data fetch from the database
     * and posts it to the [bitcoinValueUpdated] when it receives
     * the ticker event (every second)
     */
    fun getBitcoinValues() {
        viewModelScope.launch {
            for (event in tickerChannel) {
                val data = bitcoinRepo.fetchBitcoinDataFromDatabase()
                bitcoinValueUpdated.postValue(data)
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