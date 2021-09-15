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

    fun fetchImages(q: String = "") {
        viewModelScope.launch {
            val returnedImages = repository.fetchImage(q = q)
            returnedImages?.let {
                _images.value = it
            }
        }
    }

}