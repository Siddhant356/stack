package com.example.stackapi

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test

class StackoverflowAPIv3Tests {

    private val api = StackClient.api
    @Test
    fun `get stack response`()= runBlocking(){

        val response = api.getStack()
        assertNotNull(response.body())
    }
}