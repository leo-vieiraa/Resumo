package com.leovieira.resumo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leovieira.resumo.model.Image
import com.leovieira.resumo.repository.PixabayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: PixabayRepository
) : ViewModel() {

    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> = _images

    private val _page = MutableLiveData<Int>()
    val page: LiveData<Int> = _page

    private var _query: String? = null

    fun fetchImages(q: String = "", page: Int = 1) {
        viewModelScope.launch {
            val returnedImages = repository.fetchImage(q = q, page = page)
            returnedImages?.let {
                _images.value = it
            }
        }
    }

    fun nextPage() {
        _page.value = (_page.value ?: 1) + 1
    }

    fun searchFor(q : String) {
        _query = q
        _page.value = 1
    }

}