package id.tomuchcoffee.newsapp.source.news

import id.tomuchcoffee.newsapp.source.network.ApiClient
import org.koin.dsl.module

val repositoryModule = module {
    factory { NewsRepository( get(), get() ) }
}

class NewsRepository(
    private val api: ApiClient,
    val db: NewsDao
) {

    suspend fun fetch(
        categoty: String,
        query: String,
        page: Int,
    ): NewsModel{
        return api.fetchNews(
            "342d88548ce3458aab1f0367cf511f10",
            "id",
            categoty,
            query,
            page
        )
    }

    suspend fun find(articleModel: ArticleModel)= db.find(articleModel.publishedAt )

    suspend fun save(articleModel: ArticleModel){
        db.save(articleModel)
    }

    suspend fun remove(articleModel: ArticleModel){
        db.remove(articleModel)
    }
}