package com.app.androidtest.di

import android.content.Context
import androidx.room.Room
import com.app.androidtest.data.local.ShoppingDao
import com.app.androidtest.data.local.ShoppingItemDatabase
import com.app.androidtest.data.remote.PixabayAPI
import com.app.androidtest.other.Constants.BASE_URL
import com.app.androidtest.other.Constants.DATABASE_NAME
import com.app.androidtest.repositories.DefaultShoppingRepository
import com.app.androidtest.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,ShoppingItemDatabase::class.java,DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideShoppingRepository(
        dao:ShoppingDao,
        api:PixabayAPI
    ) = DefaultShoppingRepository(dao,api) as ShoppingRepository

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi():PixabayAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }



}