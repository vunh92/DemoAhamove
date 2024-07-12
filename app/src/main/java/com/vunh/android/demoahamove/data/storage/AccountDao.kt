package com.vunh.android.demoahamove.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vunh.android.demoahamove.domain.entity.AccountEntity

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(accountEntity: AccountEntity)

    @Query("SELECT * from tb_account LIMIT 1")
    fun getAccount(): LiveData<AccountEntity>
}