package c.r.myapplication.ui.home.item

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import c.r.myapplication.NewsApp
import c.r.myapplication.data.remote.model.ItemHome
import c.r.myapplication.databinding.FragmentHomeBinding
import c.r.myapplication.ui.add.AddActivity
import c.r.myapplication.ui.base.BaseFragment
import c.r.myapplication.ui.detail.DetailActivity
import c.r.myapplication.utils.SessionManager
import c.r.myapplication.ui.home.item.HomeItemContract.ContractView
import com.google.gson.Gson
import java.util.ArrayList
import javax.inject.Inject

class HomeFragment : BaseFragment(), ContractView,  HomeAdapter.OnItemClickListener {

  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!
  private var sessionManager: SessionManager? = null
  var producthome: List<ItemHome> = arrayListOf()
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
    binding.etCari.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {
      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        cari(s.toString())
      }
    })
    return root
  }

  private fun cari(string: String) {

    val ite : ArrayList<ItemHome> = arrayListOf()
    producthome.forEach {
      if (it.cr_no_hdr.contains(string, ignoreCase = false)){
        ite.add(it)
      }
    }
    if (string.length>0) mAdapter?.setItems(ite)
    else mAdapter?.setItems(producthome)
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
    producthome = items
    mAdapter?.setItems(items)
  }

  override fun onResume() {
    super.onResume()
    mPresenter.getArticlesFromApi()
  }
  override fun onItemClick(item : ItemHome) {
    val intent = Intent(context, DetailActivity::class.java)
    intent.putExtra("cr_id_hdr", item.cr_id_hdr)
    startActivity(intent)
  }
}