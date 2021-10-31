package com.example.stackit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stackapi.models.StackApiResponse
import com.example.stackit.databinding.FeedFragmentBinding


class FeedFragment : Fragment() {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private lateinit var viewModel: FeedViewModel
    private val feedAdapter = FeedRecyclerAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.updateFeed()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FeedFragmentBinding.inflate(inflater, container, false)
       binding.rvStackFeed.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStackFeed.adapter = feedAdapter
        viewModel.feed.observe({ lifecycle }){
            feedAdapter.submitList(it)
            binding.tvAvgAnsCount.text = getAverageAnswerCount(it)
            binding.tvAvgViewCount.text = getAverageViewCount(it)
        }
        return binding.root
    }

    private fun getAverageAnswerCount(items:List<StackApiResponse.Item>): String {
        var count: Int=0
        items.forEach {
            count += it.answerCount
        }
        return String.format("%.2f", (count.toDouble() / (items.size).toDouble()))
    }

    private fun getAverageViewCount(items: List<StackApiResponse.Item>): String {
        var count: Int = 0
        items.forEach {
            count += it.viewCount
        }
        return String.format("%.2f", (count.toDouble() / (items.size).toDouble()))
    }


}