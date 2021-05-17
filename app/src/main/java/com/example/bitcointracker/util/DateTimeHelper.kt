package com.example.bitcointracker.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * A helper class for converting the date and time values into
 * formatted strings
 *
 * @author Piyush Garg
 */
object DateTimeHelper {

    /**
     * This method converts the unix date value into a string of
     * Month Day, Year for that date
     *
     * @param date to be converted to string
     * @return [String] representing month day and year for the given [date]
     */
    fun getDateFromUnixTime(date: Long): String {
        val sdf = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
        return sdf.format(date)
    }

    /**
     * This method converts the unix date value into a string of
     * Hour:minutes:seconds for that date
     *
     * @param date to be converted to string
     * @return [String] representing hour, minutes, and seconds for the given [date]
     */
    fun getTimeFromUnixTime(date: Long): String {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.US)
        return sdf.format(date)
    }

    /**
     * This method converts the unix date value into a string of
     * Month, Day, Year, Hours, Minutes, and Seconds for that date
     *
     * @param date to be converted to string
     * @return [String] representing formated value for the given [date]
     */
    fun getDateAndTimeFromUnixTime(date: Long): String {
        return "${getDateFromUnixTime(date)} at ${getTimeFromUnixTime(date)}"
    }
}