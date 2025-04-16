package com.example.fetch.repo

import com.example.fetch.model.Item
import retrofit2.http.GET

interface ItemApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}
