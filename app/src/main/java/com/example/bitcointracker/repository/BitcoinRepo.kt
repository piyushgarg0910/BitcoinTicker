package com.example.bitcointracker.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.bitcointracker.application.BitcoinApplication.Companion.bitcoinApi
import com.example.bitcointracker.database.BitcoinDatabase
import com.example.bitcointracker.model.*
import com.example.bitcointracker.util.SingleLiveEvent
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

/**
 * Repository class that defines how the data is fetched from the network
 * and database, as well as stores the network response into a local database
 *
 * @author Piyush Garg
 */
class BitcoinRepo (context: Context){

    private var bitcoinDatabase: BitcoinDatabase =
        Room.databaseBuilder(context, BitcoinDatabase::class.java, "bitcoin_database")
            .build()

    /**
     * [errorFetchingData] is a Single Live Event for triggering the UI response
     * when there is an error fetching the data from the network
     */
    val errorFetchingData = SingleLiveEvent<Boolean>()

    companion object {
        private val TAG = BitcoinRepo::class.java.simpleName
    }

    /**
     * This method fetches the data from network, converts the DTOs
     * into [BitcoinValueModel], and stores/updates the response in the
     * local database
     */
    suspend fun updateBitcoinData() {
        val date = Date().time
        try {
            val data = bitcoinApi.getBitcoinData()
            val dataList = ArrayList<BitcoinValueModel>()
            dataList.add(BitcoinValueModel("United States", data.usdValue.last, date, null, data.usdValue.symbol))
            dataList.add(BitcoinValueModel("Australia", data.audValue.last, date, null, data.audValue.symbol))
            dataList.add(BitcoinValueModel("Brazil", data.brlValue.last, date, null, data.brlValue.symbol))
            dataList.add(BitcoinValueModel("Canada", data.cadValue.last, date, null, data.cadValue.symbol))
            dataList.add(BitcoinValueModel("Chile", data.clpValue.last, date, null, data.clpValue.symbol))
            dataList.add(BitcoinValueModel("China", data.cnyValue.last, date, null, data.cnyValue.symbol))
            dataList.add(BitcoinValueModel("European Union", data.eurValue.last, date, null, data.eurValue.symbol))
            dataList.add(BitcoinValueModel("United Kingdom", data.gbpValue.last, date, null, data.gbpValue.symbol))
            dataList.add(BitcoinValueModel("India", data.inrValue.last, date, null, data.inrValue.symbol))
            dataList.add(BitcoinValueModel("Korea", data.krwValue.last, date, null, data.krwValue.symbol))
            dataList.add(BitcoinValueModel("Thailand", data.thbValue.last, date, null, data.thbValue.symbol))
            dataList.add(BitcoinValueModel("Turkey", data.tryValue.last, date, null, data.tryValue.symbol))
            bitcoinDatabase.bitcoinDao().insertAll(dataList)
        } catch (ex: HttpException) {
            Log.e(TAG, "Error fetching data from network", ex)
            errorFetchingData.postValue(true)
        } catch (ex: IOException) {
            Log.e(TAG, "Error fetching data from network", ex)
            errorFetchingData.postValue(true)
        }
    }

    /**
     * This method fetches the Bitcoin data from the database.
     *
     * @return [List] of [BitcoinValueModel] Bitcoin values present in the database
     */
    suspend fun fetchBitcoinDataFromDatabase(): List<BitcoinValueModel> {
        val date = Date().time
        val data = bitcoinDatabase.bitcoinDao().getData()
        data.forEach {
            it.updatedDate = date
        }
        return data
    }
}