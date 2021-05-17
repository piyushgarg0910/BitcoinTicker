package com.example.bitcointracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class for loading the repository details in the recycler view
 * and model for storing the data to the local database.
 *
 * @author Piyush Garg
 */
@Entity
data class BitcoinValueModel(
    @PrimaryKey val country: String,
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "updated_at") var updatedDate: Long?,
    @ColumnInfo(name = "symbol") val symbol: String)