package com.app.androidtest.repositories

import androidx.lifecycle.LiveData
import com.app.androidtest.data.local.ShoppingDao
import com.app.androidtest.data.local.ShoppingItem
import com.app.androidtest.data.remote.PixabayAPI
import com.app.androidtest.data.remote.reponses.ImageResponse
import com.app.androidtest.other.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository{
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
         shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured",null)
            } else {
                Resource.error("An unknown error occured",null)
            }
        } catch (e:Exception){
            Resource.error("Could not reach the server,check your internet connection please",null)
        }
    }
}