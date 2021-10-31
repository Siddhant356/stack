package com.example.stackit.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.stackapi.models.StackApiResponse
import com.example.stackit.databinding.ListItemsBinding


class FeedRecyclerAdapter() :
    ListAdapter<StackApiResponse.Item, FeedRecyclerAdapter.FeedViewHolder>(FeedDiffCallBack()) {

    class FeedViewHolder(val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root)

    private class FeedDiffCallBack : DiffUtil.ItemCallback<StackApiResponse.Item>() {
        override fun areItemsTheSame(oldItem: StackApiResponse.Item, newItem: StackApiResponse.Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: StackApiResponse.Item, newItem: StackApiResponse.Item): Boolean {
            return oldItem.toString() == newItem.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflater = parent.context.getSystemService(LayoutInflater::class.java)
        val binding = ListItemsBinding.inflate(inflater, parent, false)
        return FeedViewHolder(binding)

    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
       val item = getItem(position)
        holder.binding.tvFirstLine.text = item.title
        holder.binding.ivProfile.load(item.owner.profileImage)

        holder.binding.tvFirstLine.setOnClickListener {  }
    }


}