package com.leovieira.resumo.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.leovieira.resumo.database.dao.PixabayDao
import com.leovieira.resumo.model.Image

@Database(
    entities = [
        Image::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getPixabayDao(): PixabayDao

    companion object {

        fun getDatabase(context: Context): AppDatabase {

            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "resumo_db"
            )
                .fallbackToDestructiveMigration()
                .build()

        }

    }


}