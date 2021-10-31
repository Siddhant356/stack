package com.example.stackit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackapi.models.StackApiResponse
import com.example.stackit.data.StackoverflowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
    private val repo = StackoverflowRepository()
    private val _feed = MutableLiveData<List<StackApiResponse.Item>>()

    val feed: LiveData<List<StackApiResponse.Item>> = _feed
    fun updateFeed() {
        viewModelScope.launch(Dispatchers.IO) {
            _feed.postValue(repo.getStackoverflowResponse())
        }

    }
}