package com.example.bitcointracker.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcointracker.R
import com.example.bitcointracker.databinding.BitcoinFragmentBinding
import com.example.bitcointracker.model.BitcoinValueModel
import com.example.bitcointracker.util.ColorHelper
import com.example.bitcointracker.util.ColorHelper.Companion.listOfColors
import com.example.bitcointracker.view.adapter.BitcoinPagerAdapter
import com.example.bitcointracker.viewModel.fragment.BitcoinFragmentViewModel

/**
 * Fragment which presents the recycler view that loads the Bitcoin values
 *
 * @author Piyush Garg
 */
class BitcoinFragment : Fragment(R.layout.bitcoin_fragment) {
    private val viewModel: BitcoinFragmentViewModel by viewModels()

    private var _binding: BitcoinFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: BitcoinPagerAdapter

    private var firstLoad = true

    private var itemList: List<BitcoinValueModel> = ArrayList()

    companion object {
        private const val LAST_ITEM_BEING_VIEWED = "BITCOIN_VALUE_LOADED"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = BitcoinFragmentBinding.inflate(inflater, container, false)
        ColorHelper().setColors()
        bindView()

        viewModel.bitcoinValueUpdated.observe(this, {
            itemList = it
            adapter.submitList(it)
            if (itemList.isEmpty())
                processViewStates(true)
            else
                processViewStates(false)
            if (firstLoad) {
                firstLoad = false
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                var itemToLoad = 0
                if (sharedPref != null)
                    itemToLoad = sharedPref.getInt(LAST_ITEM_BEING_VIEWED, 0)
                if (itemToLoad < it.size) {
                    binding.bitcoinRecyclerView.scrollToPosition(itemToLoad)
                    setColors(itemToLoad)
                }
            }
        })

        viewModel.getBitcoinValues()
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        viewModel.destroy()
        super.onDestroy()
    }

    /**
     * This method sets up the view elements
     */
    private fun bindView() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = "Bitcoin Ticker"
        binding.bitcoinRecyclerView.layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.HORIZONTAL,
            false
        )

        processViewStates(false)

        adapter = BitcoinPagerAdapter(activity as AppCompatActivity)
        binding.bitcoinRecyclerView.adapter = adapter
        setColors(0)

        binding.bitcoinRecyclerView.itemAnimator = null

        val snapHelper: LinearSnapHelper = object : LinearSnapHelper() {
            override fun findTargetSnapPosition(layoutManager: RecyclerView.LayoutManager,
                velocityX: Int, velocityY: Int): Int {
                val centerView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
                val position = layoutManager.getPosition(centerView)
                var targetPosition = -1
                if (layoutManager.canScrollHorizontally()) {
                    targetPosition = if (velocityX < 0) {
                        position - 1
                    } else {
                        position + 1
                    }
                }
                if (layoutManager.canScrollVertically()) {
                    targetPosition = if (velocityY < 0) {
                        position - 1
                    } else {
                        position + 1
                    }
                }
                val firstItem = 0
                val lastItem = layoutManager.itemCount - 1
                targetPosition = lastItem.coerceAtMost(targetPosition.coerceAtLeast(firstItem))
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                if (sharedPref != null) {
                    with(sharedPref.edit()) {
                        putInt(LAST_ITEM_BEING_VIEWED, targetPosition)
                        apply()
                    }
                }
                setColors(targetPosition)
                return targetPosition
            }
        }
        snapHelper.attachToRecyclerView(binding.bitcoinRecyclerView)
    }

    /**
     * This method is used to set colors for the navigation bar, status bar, and
     * the toolbar when the recyclerView is scrolled
     *
     * @param position is the position of the item on the recyclerView currently
     *                 visible on the screen
     */
    private fun setColors(position: Int) {
        if (position < listOfColors.size) {
            binding.toolbar
                .setBackgroundColor(getColor(requireActivity(), listOfColors[position].first))
            activity?.window?.statusBarColor =
                getColor(requireActivity(), listOfColors[position].second)
            activity?.window?.navigationBarColor =
                getColor(requireActivity(), listOfColors[position].first)
        } else {
            binding.toolbar
                .setBackgroundColor(getColor(requireActivity(), listOfColors[0].first))
            activity?.window?.statusBarColor =
                getColor(requireActivity(), listOfColors[0].second)
            activity?.window?.navigationBarColor =
                getColor(requireActivity(), listOfColors[0].first)
        }
    }

    /**
     * This method is used to process the state of data being fetched for
     * the recycler view. It shows shimmer, error text, or recyclerView
     * depending on the status of the data fetch
     *
     * @param isError indicates the the viewState is to be processed due
     *                to an error or because the data is available
     */
    private fun processViewStates(isError: Boolean) {
        if (itemList.isEmpty() && isError) {
            binding.errorState.isVisible = true
            binding.shimmerContainer.showShimmer(false)
            binding.shimmerContainer.isVisible = false
            binding.bitcoinRecyclerView.isVisible = false
        } else if (itemList.isEmpty()) {
            binding.errorState.isVisible = false
            binding.shimmerContainer.showShimmer(true)
            binding.shimmerContainer.isVisible = true
            binding.bitcoinRecyclerView.isVisible = false
        } else {
            binding.errorState.isVisible = false
            binding.shimmerContainer.showShimmer(false)
            binding.shimmerContainer.isVisible = false
            binding.bitcoinRecyclerView.isVisible = true
        }
    }
}