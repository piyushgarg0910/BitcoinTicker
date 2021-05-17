package com.example.bitcointracker.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bitcointracker.databinding.BitcoinViewBinding
import com.example.bitcointracker.model.BitcoinValueModel
import com.example.bitcointracker.util.diffUtil.BitcoinDiffUtil
import com.example.bitcointracker.view.viewHolder.BitcoinViewHolder

/**
 * Adapter for the recycler view that shows the Bitcoin values
 *
 * @author Piyush Garg
 */
class BitcoinPagerAdapter(private val context: Context)
    : ListAdapter<BitcoinValueModel, BitcoinViewHolder>(BitcoinDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitcoinViewHolder {
        val binding = BitcoinViewBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return BitcoinViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: BitcoinViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}