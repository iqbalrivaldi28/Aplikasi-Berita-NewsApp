package id.tomuchcoffee.newsapp.source.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.tomuchcoffee.newsapp.source.news.ArticleModel
import id.tomuchcoffee.newsapp.source.news.NewsDao
import id.tomuchcoffee.newsapp.util.SourceConverter

@Database(
    entities = [ArticleModel::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(SourceConverter::class)
abstract class DatabaseClient : RoomDatabase() {
    abstract val newsDao: NewsDao
}