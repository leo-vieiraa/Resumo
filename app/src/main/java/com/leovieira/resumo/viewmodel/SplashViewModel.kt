package com.leovieira.resumo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leovieira.resumo.repository.PixabayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: PixabayRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isloading : LiveData<Boolean> = _isLoading

    fun loadData(q: String = "", page: Int = 1) {

        viewModelScope.launch {
            _isLoading.value = true
            val listOfImages = repository.fetchImage(q = q, page = page)
            listOfImages?.let {
                repository.insert(it)
            }
            delay(5000)
            _isLoading.value = false
        }

    }

}