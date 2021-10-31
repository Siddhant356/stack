package com.example.stackit.data

import com.example.stackapi.StackClient
import com.example.stackapi.models.StackApiResponse



class StackoverflowRepository {
    private val api = StackClient.api
    suspend fun getStackoverflowResponse(): List<StackApiResponse.Item>? {
        val response = api.getStack()
        return response.body()?.items
    }
}