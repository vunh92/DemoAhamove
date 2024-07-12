package com.vunh.android.demoahamove.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vunh.android.demoahamove.domain.entity.PopularEntity

@Dao
interface PopularDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movieEntities: MutableList<PopularEntity>)

    @Query("SELECT * FROM tb_popular ORDER BY id DESC")
    fun getAll(): LiveData<MutableList<PopularEntity>>
}