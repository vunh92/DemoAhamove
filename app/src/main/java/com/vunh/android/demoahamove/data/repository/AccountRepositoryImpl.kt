package com.vunh.android.demoahamove.data.repository

import androidx.lifecycle.LiveData
import com.vunh.android.demoahamove.data.remote.ApiService
import com.vunh.android.demoahamove.data.storage.AppDatabase
import com.vunh.android.demoahamove.domain.entity.AccountEntity
import com.vunh.android.demoahamove.domain.repository.AccountRepository
import com.vunh.android.demoahamove.domain.usecase.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : AccountRepository {
    override suspend fun getUser(): UseCaseResult<AccountEntity> {
        return try {
            val account = withContext(Dispatchers.IO) {
                apiService.callGoogleAsync().await()
            }
            withContext(Dispatchers.IO) {
                insertAccount(account)
            }
            UseCaseResult.Success(account)
        } catch (ex: Throwable) {
            UseCaseResult.Error(ex.message.orEmpty())
        }
    }

    override suspend fun insertAccount(account: AccountEntity) {
        appDatabase.accountDao.insert(account)
    }

    override val account: LiveData<AccountEntity>
        get() = appDatabase.accountDao.getAccount()
}