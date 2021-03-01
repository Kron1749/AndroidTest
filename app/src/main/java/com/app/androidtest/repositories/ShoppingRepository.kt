package com.app.androidtest.repositories

import androidx.lifecycle.LiveData
import com.app.androidtest.data.local.ShoppingItem
import com.app.androidtest.data.remote.reponses.ImageResponse
import com.app.androidtest.other.Resource
import retrofit2.Response

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems():LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery:String):Resource<ImageResponse>
}