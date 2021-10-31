package com.example.stackapi.apis

import com.example.stackapi.models.StackApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface StackoverflowAPIv3 {
    @GET("questions?key=ZiXCZbWaOwnDgpVT9Hx8IA((&order=desc&sort=activity&site=stackoverflow")
    suspend fun getStack(): Response<StackApiResponse>
}