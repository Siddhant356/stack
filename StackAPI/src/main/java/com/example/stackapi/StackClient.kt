package com.example.stackapi

import com.example.stackapi.apis.StackoverflowAPIv3
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object StackClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.stackexchange.com/2.2/")
            .build()
    }
    val api: StackoverflowAPIv3 by lazy {
        retrofit.create(StackoverflowAPIv3::class.java)
    }
}