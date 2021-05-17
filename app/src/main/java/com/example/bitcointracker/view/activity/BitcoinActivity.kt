package com.example.bitcointracker.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.example.bitcointracker.view.fragment.BitcoinFragment
import com.example.bitcointracker.R
import com.example.bitcointracker.repository.BitcoinRepo
import com.example.bitcointracker.viewModel.activity.BitcoinActivityViewModel

/**
 * Activity class which hosts the Bitcoin Fragment
 *
 * @author Piyush Garg
 */
class BitcoinActivity : AppCompatActivity() {

    private val viewModel : BitcoinActivityViewModel by viewModels()

    companion object {
        lateinit var bitcoinRepo: BitcoinRepo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bitcoin_activity)

        bitcoinRepo = BitcoinRepo(this)

        viewModel.updateBitcoinValues()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.bitcoinContainer, BitcoinFragment())
        }

        bitcoinRepo.errorFetchingData.observe(this, {
            Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroy() {
        viewModel.destroy()
        super.onDestroy()
    }
}