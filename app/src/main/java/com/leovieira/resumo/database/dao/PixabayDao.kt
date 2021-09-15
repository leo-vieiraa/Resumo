package com.leovieira.resumo.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.leovieira.resumo.model.Image

@Dao
interface PixabayDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(list: List<Image>)

    @Query("SELECT * FROM image")
    suspend fun fetch(): List<Image>

}