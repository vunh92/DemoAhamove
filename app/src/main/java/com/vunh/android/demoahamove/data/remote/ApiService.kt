package com.vunh.android.demoahamove.data.remote

import com.vunh.android.demoahamove.domain.entity.AccountEntity
import com.vunh.android.demoahamove.domain.entity.PopularEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("google")
    fun callGoogleAsync(
    ): Deferred<AccountEntity>

    @GET("google/repos?page=0&per_page=20")
    fun callListAsync(
    ): Deferred<MutableList<PopularEntity>>

    @GET("google/repos")
    fun fetchListAsync(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
    ): Deferred<MutableList<PopularEntity>>
}