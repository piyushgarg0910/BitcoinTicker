package com.example.bitcointracker.database

import androidx.room.*
import com.example.bitcointracker.model.BitcoinValueModel

/**
 * This is a data access object class for inserting, deleting, and
 * fetching data from the database for the bitcoin values
 *
 * @author Piyush Garg
 */

@Dao
interface BitcoinDao {

    /**
     * Gets a list of all the Bitcoin values currently in the database
     *
     * @return List of all [BitcoinValueModel] present in the database
     */
    @Query("SELECT * FROM BitcoinValueModel")
    suspend fun getData(): List<BitcoinValueModel>

    /**
     * Inserts a list of Bitcoin values into the database
     *
     * @param values is a list of [BitcoinValueModel] to be inserted into
     * the database. [OnConflictStrategy] will replace the current values
     * if entry is already present in the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(values: List<BitcoinValueModel>)

    /**
     * Deletes all the entries from the database
     */
    @Query("DELETE FROM BitcoinValueModel")
    suspend fun deleteAll()
}