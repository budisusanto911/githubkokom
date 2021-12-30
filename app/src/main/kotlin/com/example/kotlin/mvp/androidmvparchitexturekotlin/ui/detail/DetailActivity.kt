package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.detail

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.kotlin.mvp.androidmvparchitexturekotlin.NewsApp
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.ItemHome
import com.example.kotlin.mvp.androidmvparchitexturekotlin.databinding.ActivityDetailHomeBinding
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.BaseActivity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.detail.DetailContract.ContractView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.item.HomeAdapter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.utils.SessionManager
import com.google.gson.Gson
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

class DetailActivity : BaseActivity(), ContractView, DetailAdapter.OnItemClickListener{

    private final val TAG = DetailActivity::class.java.simpleName
    private lateinit var binding : ActivityDetailHomeBinding
    private var mAdapter: DetailAdapter? = null
    @Inject
    lateinit var mPresenter: DetailPresenter

    private lateinit var sessionManager: SessionManager
    init {

        NewsApp.mNewsComponent?.inject(this)

        mPresenter.attachView(this)
        mAdapter = this?.let { DetailAdapter(it, ArrayList<ItemHome>(), this) }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHomeBinding.inflate(layoutInflater)
        setContentView( binding.root)
        sessionManager = SessionManager(this)
        sessionManager.init()
        setUp()
        val id = intent.getStringExtra("cr_id_hdr")
        if (id != null) {
            mPresenter.getArticlesFromApi(id)
        }

    }

    override fun setUp() {
        binding.run {
            val mLayoutManager = LinearLayoutManager(this@DetailActivity)
            list.layoutManager = mLayoutManager
            list.addItemDecoration(DividerItemDecoration(this@DetailActivity, LinearLayoutManager.VERTICAL))
            list.adapter = mAdapter
        }

    }


    override fun onArtilesReady(items: List<ItemHome>) {

        mAdapter?.setItems(items)
    }



    override fun onDestroy() {

        mPresenter.detachView()
        super.onDestroy()
    }

    override fun onItemClick(item: ItemHome) {

    }

}