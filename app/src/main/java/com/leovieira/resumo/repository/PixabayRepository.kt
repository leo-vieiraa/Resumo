package com.leovieira.resumo.repository

import com.leovieira.resumo.database.dao.PixabayDao
import com.leovieira.resumo.model.Image
import com.leovieira.resumo.service.PixabayApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PixabayRepository @Inject constructor(
    private val service: PixabayApi,
    private val pixabayDao: PixabayDao
) {

    suspend fun fetchImage(q: String, page: Int): List<Image>? {
        return withContext(Dispatchers.Default) {
            val response = service.fetchImage(q = q, page = page)
            val processedResponse = processData(response)
            processedResponse?.hits
        }
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun insert(listOfImage: List<Image>): Boolean {
        return withContext(Dispatchers.Default) {
            pixabayDao.insert(listOfImage)
            true
        }
    }

}