package com.example.fetch.repo


import com.example.fetch.model.Item
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

object ItemRepository {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val api = retrofit.create(ItemApiService::class.java)

    suspend fun getItems(): List<Item> = api.getItems()
}
