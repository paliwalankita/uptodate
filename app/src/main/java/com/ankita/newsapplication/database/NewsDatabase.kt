package com.ankita.newsapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ankita.newsapplication.database.data.Article

@Database(entities = [Article::class], version = 1 , exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

    companion object {
        @Volatile
        private var newsDatabaseInstance: NewsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = newsDatabaseInstance ?: synchronized(LOCK) {
            newsDatabaseInstance ?: createDatabase(context).also { newsDatabaseInstance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news_article_db.db"
            ).build()
    }
}