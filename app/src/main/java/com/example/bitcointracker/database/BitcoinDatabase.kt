package com.example.bitcointracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitcointracker.model.BitcoinValueModel

/**
 * This class defines the database for storing the Bitcoin values in the
 * local database
 *
 * @author Piyush Garg
 */
@Database(entities = [BitcoinValueModel::class], version = 1, exportSchema = false)
abstract class BitcoinDatabase : RoomDatabase() {
    abstract fun bitcoinDao(): BitcoinDao
}
