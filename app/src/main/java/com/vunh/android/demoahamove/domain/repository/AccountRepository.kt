package com.vunh.android.demoahamove.domain.repository

import androidx.lifecycle.LiveData
import com.vunh.android.demoahamove.domain.entity.AccountEntity
import com.vunh.android.demoahamove.domain.usecase.UseCaseResult

interface AccountRepository {
    suspend fun getUser(): UseCaseResult<AccountEntity>
    suspend fun insertAccount(account: AccountEntity)
    val account: LiveData<AccountEntity>
}