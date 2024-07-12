package com.vunh.android.demoahamove.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vunh.android.demoahamove.data.repository.AccountRepositoryImpl
import com.vunh.android.demoahamove.data.repository.PopularRepositoryImpl
import com.vunh.android.demoahamove.domain.entity.PopularEntity
import com.vunh.android.demoahamove.domain.usecase.UseCaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accountRepository: AccountRepositoryImpl,
    private val popularRepository: PopularRepositoryImpl,
    private val dispatcher: CoroutineDispatcher,
): ViewModel() , CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()
    val showPopularListStorage = MutableLiveData<Boolean>()

    val accountGoogle = accountRepository.account

    /* not paging */
    val popularList = popularRepository.popularList

    /* paging */
    val populars: LiveData<PagingData<PopularEntity>> =
        popularRepository.getPopulars().cachedIn(viewModelScope)

    init {
        getAccount()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    private fun getAccount() {
        showLoading.value = true
        viewModelScope.launch {
            try {
                val result = withContext(dispatcher){
                    accountRepository.getUser()
                }
                when (result) {
                    is UseCaseResult.Success -> {}
                    is UseCaseResult.Error -> {
                        showError.value = result.errorMessage
                        showPopularListStorage.value = true
                    }
                }

            } catch (networkError: IOException) {
                showError.value = networkError.message
            } finally {
                showLoading.value = false
            }

        }
    }

    private fun getPopularList() {
        showLoading.value = true
        viewModelScope.launch {
            try {
                var result = withContext(dispatcher){
                    popularRepository.getPopularList()
                }
                showLoading.value= false
                when (result) {
                    is UseCaseResult.Success -> {}
                    is UseCaseResult.Error -> {
                        showError.value = result.errorMessage
                    }
                }

            } catch (networkError: IOException) {
                showError.value = networkError.message
            } finally {
                showLoading.value= false
            }

        }
    }
}