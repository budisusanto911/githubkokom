package c.r.myapplication.ui.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import c.r.myapplication.NewsApp
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.databinding.ActivityNewsBinding
import c.r.myapplication.ui.base.BaseActivity
import c.r.myapplication.ui.news.NewsAdapter.OnItemClickListener
import c.r.myapplication.ui.news.NewsContract.ContractView
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

class NewsActivity : BaseActivity(), ContractView, OnItemClickListener {
    private lateinit var binding: ActivityNewsBinding
    private var mAdapter: NewsAdapter? = null

    @Inject
    lateinit var mPresenter: NewsPresenter

    init {
        NewsApp.mNewsComponent?.inject(this)
        mPresenter.attachView(this)
        mAdapter = NewsAdapter(this, ArrayList<ArticleEntity>(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
        mPresenter.getArticles(this)
    }

    override fun setUp() {
        binding.run {
            val mLayoutManager = LinearLayoutManager(this@NewsActivity)
            recyclerview.layoutManager = mLayoutManager
            recyclerview.addItemDecoration(DividerItemDecoration(this@NewsActivity, LinearLayoutManager.VERTICAL))
            recyclerview.adapter = mAdapter
        }
    }

    override fun onArtilesReady(items: List<ArticleEntity>) {
        if (items.isNotEmpty()) {
            hideErrorContainer()
            mAdapter?.setItems(items)
        } else {
            showErrorContainer()
        }

    }

    override fun onItemClick(item : ArticleEntity) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
    }

    private fun showErrorContainer() {
        binding.contentError.contentError.visibility = View.VISIBLE
    }

    private fun hideErrorContainer() {
        binding.contentError.contentError.visibility = View.GONE

    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

}