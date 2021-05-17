package com.example.bitcointracker.util.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.bitcointracker.model.BitcoinValueModel

/**
 * A DiffUtil class for calculating the difference between the Bitcoin values
 * to be displayed so that the ListAdapter can be updated accordingly
 *
 * @author Piyush Garg
 */
class BitcoinDiffUtil : DiffUtil.ItemCallback<BitcoinValueModel>() {

    override fun areItemsTheSame(oldItem: BitcoinValueModel, newItem: BitcoinValueModel): Boolean {
        return oldItem.country == newItem.country
    }

    override fun areContentsTheSame(
        oldItem: BitcoinValueModel,
        newItem: BitcoinValueModel
    ): Boolean {
        return oldItem.updatedDate == newItem.updatedDate
    }
}