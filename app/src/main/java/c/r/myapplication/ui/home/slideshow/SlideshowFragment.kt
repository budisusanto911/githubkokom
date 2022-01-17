package c.r.myapplication.ui.home.slideshow

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import c.r.myapplication.NewsApp
import c.r.myapplication.data.remote.model.ItemHome
import c.r.myapplication.databinding.FragmentSlideshowBinding
import c.r.myapplication.ui.base.BaseFragment
import c.r.myapplication.ui.detail.DetailActivity
import c.r.myapplication.ui.home.item.HomeAdapter
import c.r.myapplication.ui.home.slideshow.SearchContract.ContractView
import java.util.ArrayList
import java.util.Calendar
import javax.inject.Inject

class SlideshowFragment : BaseFragment(), ContractView,  HomeAdapter.OnItemClickListener  {

  private var _binding: FragmentSlideshowBinding? = null
  private var mAdapter: HomeAdapter? = null
  @Inject
  lateinit var mPresenter: SearchPresenter

  private val binding get() = _binding!!
  override fun initViews() {

  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
    val root: View = binding.root
    NewsApp.mNewsComponent?.inject(this)
    mPresenter.attachView(this)
    mAdapter = context?.let { HomeAdapter(it, ArrayList<ItemHome>(), this) }

    binding.run {
      val mLayoutManager = LinearLayoutManager(context)
      list.layoutManager = mLayoutManager
      list.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
      list.adapter = mAdapter
      cari.setOnClickListener {
        mPresenter.getArticlesFromApi(date1.text.toString(), date2.text.toString())
      }
      date1.setOnClickListener {
        showCalendar(date1)
      }
      date2.setOnClickListener {
        showCalendar(date2)
      }
    }
    return root
  }

  @SuppressLint("SetTextI18n")
  private fun showCalendar(textView: TextView) {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val dpd = DatePickerDialog(textView.context,
      DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        textView.text = "" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth
      }, year, month, day)

    dpd.show()
  }


  override fun onArtilesReady(items: List<ItemHome>) {
    mAdapter?.setItems(items)
  }

  override fun onItemClick(item: ItemHome) {
    val intent = Intent(context, DetailActivity::class.java)
    intent.putExtra("cr_id_hdr", item.cr_id_hdr)
    startActivity(intent)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}