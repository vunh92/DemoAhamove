package com.vunh.android.demoahamove.domain.repository

import androidx.lifecycle.LiveData
import com.vunh.android.demoahamove.domain.entity.PopularEntity
import com.vunh.android.demoahamove.domain.usecase.UseCaseResult

interface PopularRepository {
    suspend fun fetchPopularList(
        page: Int,
        perPage: Int,
    ): UseCaseResult<MutableList<PopularEntity>>
    suspend fun getPopularList(): UseCaseResult<MutableList<PopularEntity>>
    suspend fun insertAll(entities: MutableList<PopularEntity>)
    val popularList: LiveData<MutableList<PopularEntity>>
}