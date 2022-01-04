package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.item

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.util.TypedValue
import com.example.kotlin.mvp.androidmvparchitexturekotlin.NewsApp
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.ItemHome
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Login
import com.example.kotlin.mvp.androidmvparchitexturekotlin.databinding.FragmentHomeBinding
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add.AddActivity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.BaseFragment
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.detail.DetailActivity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.item.HomeItemContract.ContractView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.utils.SessionManager
import com.google.gson.Gson
import java.util.ArrayList
import javax.inject.Inject

class HomeFragment : BaseFragment(), ContractView,  HomeAdapter.OnItemClickListener {

  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!
  private var sessionManager: SessionManager? = null
  private var mAdapter: HomeAdapter? = null
  @Inject
  lateinit var mPresenter: HomeItemPresenter

  override fun initViews() {


  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    NewsApp.mNewsComponent?.inject(this)
    mPresenter.attachView(this)
    mAdapter = context?.let { HomeAdapter(it, ArrayList<ItemHome>(), this) }
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root
    setUp()
    sessionManager = SessionManager(context)
    sessionManager?.init()
    val user = sessionManager?.getUserDetails()
    Log.i("TAG", "onCreateView: "+ Gson().toJson(user))
    binding.itemName.text = user?.get(sessionManager?.KEY_NAMA)
    mPresenter.getArticlesFromApi()
    binding.add.setOnClickListener {
      val intent = Intent(it.context, AddActivity::class.java)
      startActivity(intent)
    }
    val tv = TypedValue()
    if (requireActivity().theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
      val actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
      val param = binding.add.layoutParams as ViewGroup.MarginLayoutParams
      val size = actionBarHeight/5
      param.setMargins(0,0,0,actionBarHeight+size)
      binding.add.layoutParams = param
    }
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  override fun setUp() {
    binding.run {
      val mLayoutManager = LinearLayoutManager(context)
      list.layoutManager = mLayoutManager
      list.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
      list.adapter = mAdapter
    }

  }

  override fun onArtilesReady(items: List<ItemHome>) {
    mAdapter?.setItems(items)
  }

  override fun onItemClick(item : ItemHome) {
    val intent = Intent(context, DetailActivity::class.java)
    intent.putExtra("cr_id_hdr", item.cr_id_hdr)
    startActivity(intent)
  }
}