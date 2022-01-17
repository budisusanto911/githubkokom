package c.r.myapplication.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import c.r.myapplication.NewsApp
import c.r.myapplication.R
import c.r.myapplication.data.remote.model.ItemHome
import c.r.myapplication.databinding.ActivityDetailHomeBinding
import c.r.myapplication.ui.adddetail.AddDetailActivity
import c.r.myapplication.ui.base.BaseActivity
import c.r.myapplication.utils.SessionManager
import c.r.myapplication.utils.toRupiah
import c.r.myapplication.ui.detail.DetailAdapter.OnItemClickListener
import c.r.myapplication.ui.detail.DetailContract.ContractView
import com.bumptech.glide.Glide
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

class DetailActivity : BaseActivity(), ContractView, OnItemClickListener {

    private final var id: String? = null
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
    override fun setLink(link: String) {
        Glide.with(this)
            .load(link)
            .error(R.drawable.icon)
            .into(binding.ivFile)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHomeBinding.inflate(layoutInflater)
        setContentView( binding.root)
        sessionManager = SessionManager(this)
        sessionManager.init()
        setUp()
        id = intent.getStringExtra("cr_id_hdr")
        if (id != null) {
            mPresenter.getArticlesFromApi(id?:"")
        }

        binding.add.setOnClickListener {
            val intent = Intent(it.context, AddDetailActivity::class.java)
            intent.putExtra("cr_id_hdr", id)
            intent.putExtra("cr_tanggal", binding.tgl.text.toString())
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        mPresenter.getArticlesFromApi(id ?:"")
    }

    override fun setTotal(item: ItemHome, totalItem: Double) {
        binding.run {

            no.text = item.cr_no_hdr
            total.text = toRupiah(totalItem)
            tgl.text = item.cr_tanggal
            item.cr_foto?.let {
                Log.i(TAG, "setTotal: " + it)
                setLink(it)
            }
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home->   {
                onBackPressed()
                return true
            }
            // Action goes here
            else -> super.onOptionsItemSelected(item)
        }
    }
    @SuppressLint("SetTextI18n")
    override fun setUp() {
        binding.run {
            setSupportActionBar(toolbarView.toolbar)
            toolbarView.tvPageTitle.text = "Detail item"
            val mLayoutManager = LinearLayoutManager(this@DetailActivity)
            list.layoutManager = mLayoutManager
            list.addItemDecoration(DividerItemDecoration(this@DetailActivity, LinearLayoutManager.VERTICAL))
            list.adapter = mAdapter

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
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