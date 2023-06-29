package id.tomuchcoffee.newsapp.ui.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.tomuchcoffee.newsapp.databinding.CustomToolbarBinding
import id.tomuchcoffee.newsapp.databinding.FragmentBookmarkBinding
import id.tomuchcoffee.newsapp.source.news.ArticleModel
import id.tomuchcoffee.newsapp.ui.detail.DetailActivity
import id.tomuchcoffee.newsapp.ui.news.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val bookmarkModule = module {
    factory { BookmarkFragment() }
}

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: BookmarkViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingToolbar.tittle = viewModel.tittle
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        NewsAdapter.VIEW_TYPE = 2
        binding.listBookmark.adapter = newsAdapter
        viewModel.articles.observe(viewLifecycleOwner, {
            newsAdapter.clear()
            newsAdapter.add(it)
        })
    }

    private val newsAdapter by lazy {
        NewsAdapter(arrayListOf(), object : NewsAdapter.OnAdapterListener {
            override fun onClick(articleModel: ArticleModel) {
                startActivity(
                    Intent(requireActivity(), DetailActivity::class.java)
                        .putExtra("intent_detail", articleModel)
                )
            }
        })
    }
}