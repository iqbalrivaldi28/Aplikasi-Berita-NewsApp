package id.tomuchcoffee.newsapp.ui.bookmark

import androidx.lifecycle.ViewModel
import id.tomuchcoffee.newsapp.source.news.NewsRepository
import org.koin.dsl.module

val bookmarkViewModel = module {
    factory { BookmarkViewModel(get()) }
}

class BookmarkViewModel(
    val repository: NewsRepository
): ViewModel() {

    val tittle = "Disimpan"
    val articles = repository.db.findAll()
}