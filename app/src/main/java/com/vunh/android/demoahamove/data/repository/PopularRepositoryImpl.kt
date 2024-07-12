package com.vunh.android.demoahamove.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.vunh.android.demoahamove.data.remote.ApiService
import com.vunh.android.demoahamove.data.storage.AppDatabase
import com.vunh.android.demoahamove.domain.entity.PopularEntity
import com.vunh.android.demoahamove.domain.repository.PopularRepository
import com.vunh.android.demoahamove.domain.usecase.UseCaseResult
import com.vunh.android.demoahamove.presentation.paging.PopularPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : PopularRepository {
    override suspend fun getPopularList(): UseCaseResult<MutableList<PopularEntity>> {
        return try {
            val popularList = withContext(Dispatchers.IO) {
                apiService.callListAsync().await()
            }
            withContext(Dispatchers.IO) {
                if(popularList.isNotEmpty()) insertAll(popularList.toMutableList())
            }
            UseCaseResult.Success(popularList.toMutableList())
        } catch (ex: Throwable) {
            UseCaseResult.Error(ex.message.orEmpty())
        }
    }

    override suspend fun insertAll(entities: MutableList<PopularEntity>) {
        appDatabase.popularDao.insertAll(entities)
    }

    override val popularList: LiveData<MutableList<PopularEntity>>
        get() = appDatabase.popularDao.getAll()

    fun getPopulars(): LiveData<PagingData<PopularEntity>> = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 200),
        pagingSourceFactory = { PopularPagingSource(this) }
    ).liveData

    override suspend fun fetchPopularList(
        page: Int,
        perPage: Int,
    ): UseCaseResult<MutableList<PopularEntity>> {
        return try {
            val popularList = withContext(Dispatchers.IO) {
                apiService.fetchListAsync(page, perPage).await()
            }
            withContext(Dispatchers.IO) {
                if(popularList.isNotEmpty()) insertAll(popularList.toMutableList())
            }
            UseCaseResult.Success(popularList.toMutableList())
        } catch (ex: Throwable) {
            UseCaseResult.Error(ex.message.orEmpty())
        }
    }
}