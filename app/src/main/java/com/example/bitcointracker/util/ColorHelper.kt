package com.example.bitcointracker.util

import com.example.bitcointracker.R

/**
 * This is a helper class used for getting the colors to drive the
 * view when the recyclerView is scrolled
 *
 * @author Piyush Garg
 */
class ColorHelper {

    companion object {
        val listOfColors = ArrayList<Pair<Int,Int>>()
    }

    /**
     * This method inserts colors into the [listOfColors]
     */
    fun setColors() {
        listOfColors.add(Pair(R.color.color_1, R.color.color_1_dark))
        listOfColors.add(Pair(R.color.color_2, R.color.color_2_dark))
        listOfColors.add(Pair(R.color.color_3, R.color.color_3_dark))
        listOfColors.add(Pair(R.color.color_4, R.color.color_4_dark))
        listOfColors.add(Pair(R.color.color_5, R.color.color_5_dark))
        listOfColors.add(Pair(R.color.color_6, R.color.color_6_dark))
        listOfColors.add(Pair(R.color.color_7, R.color.color_7_dark))
        listOfColors.add(Pair(R.color.color_8, R.color.color_8_dark))
        listOfColors.add(Pair(R.color.color_9, R.color.color_9_dark))
        listOfColors.add(Pair(R.color.color_10, R.color.color_10_dark))
        listOfColors.add(Pair(R.color.color_11, R.color.color_11_dark))
        listOfColors.add(Pair(R.color.color_12, R.color.color_12_dark))
        listOfColors.add(Pair(R.color.color_13, R.color.color_13_dark))
    }
}