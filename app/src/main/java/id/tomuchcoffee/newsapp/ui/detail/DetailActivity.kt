package id.tomuchcoffee.newsapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import id.tomuchcoffee.newsapp.R
import id.tomuchcoffee.newsapp.databinding.ActivityDetailBinding
import id.tomuchcoffee.newsapp.databinding.CustomToolbarBinding
import id.tomuchcoffee.newsapp.source.news.ArticleModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val detailModul = module {
    factory { DetailActivity() }
}
class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: DetailViewModel by viewModel()
    private  val detail by lazy {
        intent.getSerializableExtra("intent_detail") as ArticleModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingToolbar = binding.toolbar
        setContentView(binding.root)

        setSupportActionBar( bindingToolbar.container)

        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(false )
        }

        detail.let {
            viewModel.find( it )
            val web = binding.webView
            web.loadUrl( it.url!! )
            web.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressTop.visibility = View.GONE
                }
            }
            val settings = binding.webView.settings
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = false
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bookmark, menu)
        val menuBookmark = menu!!.findItem(R.id.action_bookmark)
        menuBookmark.setOnMenuItemClickListener {
            viewModel.bookmark( detail )
            menuBookmark.setIcon(R.drawable.ic_check)
            true
        }
        viewModel.isBookmark.observe(this, {
            if (it == 0) menuBookmark.setIcon(R.drawable.ic_add)
            else menuBookmark.setIcon(R.drawable.ic_check)
        })
        return super.onCreateOptionsMenu(menu)
    }
}