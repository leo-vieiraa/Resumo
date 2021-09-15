package com.leovieira.resumo.di

import android.content.Context
import com.leovieira.resumo.database.dao.PixabayDao
import com.leovieira.resumo.database.AppDatabase
import com.leovieira.resumo.service.PixabayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providePixabayApi(retrofit: Retrofit): PixabayApi =
        retrofit.create(PixabayApi::class.java)

    @Provides
    fun providePixabayDao(@ApplicationContext context: Context): PixabayDao{
        return AppDatabase.getDatabase(context).getPixabayDao()
    }


}