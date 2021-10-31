package com.example.stackit.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.stackapi.models.StackApiResponse
import com.example.stackit.databinding.ListItemsBinding
import java.text.SimpleDateFormat
import java.util.*





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
        holder.binding.tvSecondLine.text= item.owner.displayName
        holder.binding.tvCreatedAt.text = getDateTime(item.creationDate)
        holder.binding.ivProfile.load(item.owner.profileImage)
        holder.binding.tvFirstLine.setOnClickListener{
            val uri: Uri = Uri.parse(item.link) // missing 'http://' will cause crashed

            val intent = Intent(Intent.ACTION_VIEW, uri)
            if (isOnline(context = holder.binding.tvFirstLine.context))
            startActivity(holder.binding.tvFirstLine.context, intent, Bundle())
            else{
                Toast.makeText(holder.binding.tvFirstLine.context, "Check Your Connection", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun getDateTime(c: Int): String? {
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val netDate = Date(c.toLong() * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}

