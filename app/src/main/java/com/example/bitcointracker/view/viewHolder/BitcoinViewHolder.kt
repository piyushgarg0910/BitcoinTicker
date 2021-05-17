package com.example.bitcointracker.view.viewHolder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcointracker.R
import com.example.bitcointracker.databinding.BitcoinViewBinding
import com.example.bitcointracker.model.BitcoinValueModel
import com.example.bitcointracker.util.ColorHelper.Companion.listOfColors
import com.example.bitcointracker.util.DateTimeHelper
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

/**
 * View holder that shows the Bitcoin value data for each page of the recycler view
 *
 * @author Piyush Garg
 */
class BitcoinViewHolder(private val binding: BitcoinViewBinding, private val context: Context):
    RecyclerView.ViewHolder(binding.root) {

    /**
     * This method binds the data to the view elements
     *
     * @param value entry for the page to be displayed in the viewHolder
     * @param position of the entry in the recyclerView
     */
    fun bind(value: BitcoinValueModel, position: Int) {
        binding.bitcoinValue.text =
                NumberFormat
                        .getNumberInstance(Locale.US)
                        .format(BigDecimal(value.value)
                                .setScale(2, RoundingMode.HALF_EVEN).toDouble())
        binding.bitcoinUpdateTime.text = DateTimeHelper.getTimeFromUnixTime(value.updatedDate!!)
        binding.bitcoinUpdateDate.text = DateTimeHelper.getDateFromUnixTime(value.updatedDate!!)
        binding.bitcoinCurrency.text = value.symbol
        binding.bitcoinCountry.text = value.country
        binding.bitcoinUpdateAtDate.text = context.getString(R.string.updated_time_stamp, DateTimeHelper.getDateAndTimeFromUnixTime(value.date))
        setColors(position)
    }

    /**
     * This method is used to set colors for the view elements in the
     * viewHolder
     *
     * @param position is the position of the item on the recyclerView
     */
    private fun setColors(position: Int) {
        if (position < listOfColors.size) {
            binding.bitcoinCurrencyContainer.setCardBackgroundColor(context.getColor(listOfColors[position].first))
            binding.bitcoinUpdateAt.setTextColor(context.getColor(listOfColors[position].first))
            binding.bitcoinUpdateDate.setTextColor(context.getColor(listOfColors[position].first))
            binding.bitcoinUpdateTime.setTextColor(context.getColor(listOfColors[position].first))
            binding.bitcoinValue.setTextColor(context.getColor(listOfColors[position].first))
            binding.bitcoinUpdateAtDate.setTextColor(context.getColor(listOfColors[position].first))
        } else {
            binding.bitcoinCurrencyContainer.setCardBackgroundColor(context.getColor(listOfColors[0].first))
            binding.bitcoinUpdateAt.setTextColor(context.getColor(listOfColors[0].first))
            binding.bitcoinUpdateDate.setTextColor(context.getColor(listOfColors[0].first))
            binding.bitcoinUpdateTime.setTextColor(context.getColor(listOfColors[0].first))
            binding.bitcoinValue.setTextColor(context.getColor(listOfColors[0].first))
            binding.bitcoinUpdateAtDate.setTextColor(context.getColor(listOfColors[0].first))
        }
    }
}