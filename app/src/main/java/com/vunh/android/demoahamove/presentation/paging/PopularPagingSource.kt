package com.vunh.android.demoahamove.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vunh.android.demoahamove.domain.entity.PopularEntity
import com.vunh.android.demoahamove.domain.repository.PopularRepository
import com.vunh.android.demoahamove.domain.usecase.UseCaseResult

class PopularPagingSource(
    private val repository: PopularRepository,
): PagingSource<Int, PopularEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularEntity> {
        return try {
            val position = params.key ?: 1
//            val response = apiService.fetchListAsync(position, 20).await()
            var result = repository.fetchPopularList(position, 20)
            when (result) {
                is UseCaseResult.Success -> {
                    LoadResult.Page(
                        data = result.data,
                        prevKey = if (position == 1) null else (position - 1),
                        nextKey = if (result.data.size < 20) null else (position + 1)
                    )
                }
                is UseCaseResult.Error -> {
                    LoadResult.Error(throw Exception("No Response"))
                }
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularEntity>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}