package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.gallery

import android.R.layout
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.NewsApp
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.ItemHome
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.ItemSpinner
import com.example.kotlin.mvp.androidmvparchitexturekotlin.databinding.FragmentGalleryBinding
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.BaseFragment
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.gallery.GalleryContract.ContractView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.item.HomeAdapter
import java.util.ArrayList
import java.util.Calendar
import javax.inject.Inject

class GalleryFragment : BaseFragment(), ContractView,  HomeAdapter.OnItemClickListener {

  private lateinit var galleryViewModel: GalleryViewModel
  private var _binding: FragmentGalleryBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
  private var mAdapter: HomeAdapter? = null
  @Inject
  lateinit var mPresenter: GalleryPresenter
  override fun initViews() {

  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    NewsApp.mNewsComponent?.inject(this)
    mPresenter.attachView(this)
    mAdapter = context?.let { HomeAdapter(it, ArrayList<ItemHome>(), this) }
    _binding = FragmentGalleryBinding.inflate(inflater, container, false)
    val root: View = binding.root
     binding.run {
       tanggal.setOnClickListener {
         showCalendar()
       }
       val mLayoutManager = LinearLayoutManager(context)
       list.layoutManager = mLayoutManager
       list.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
       list.adapter = mAdapter
     }
    mPresenter.getKategori()

    return root
  }


  override fun setKategori(list: ArrayList<ItemSpinner>) {
    val arrayList: ArrayList<String> = arrayListOf()
    list.forEach {
      arrayList.add(it.item)
    }
    val aa = context?.let { ArrayAdapter(it, layout.simple_spinner_item, arrayList) }
    aa?.setDropDownViewResource(layout.simple_spinner_dropdown_item)
    binding.kategori.adapter = aa
    binding.kategori.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
      override fun onNothingSelected(parent: AdapterView<*>?) {

      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mPresenter.getSubKategori(arrayList[position])
      }

    }
  }

  override fun onArtilesReady(items: List<ItemHome>) {
    mAdapter?.setItems(items)
  }


  override fun setSubKategori(list: ArrayList<ItemSpinner>) {
    val arrayList: ArrayList<String> = arrayListOf()
    list.forEach {
      arrayList.add(it.item)
    }
    val aa = context?.let { ArrayAdapter(it, layout.simple_spinner_item, arrayList) }
    aa?.setDropDownViewResource(layout.simple_spinner_dropdown_item)
    binding.subKategori.adapter = aa
    binding.subKategori.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
      override fun onNothingSelected(parent: AdapterView<*>?) {

      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mPresenter.getPos(arrayList[position])
      }

    }
  }

  override fun setPos(list: ArrayList<ItemSpinner>) {
    val arrayList: ArrayList<String> = arrayListOf()
    list.forEach {
      arrayList.add(it.item)
    }
    val aa = context?.let { ArrayAdapter(it, layout.simple_spinner_item, arrayList) }
    aa?.setDropDownViewResource(layout.simple_spinner_dropdown_item)
    binding.detailKategori.adapter = aa
    binding.detailKategori.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
      override fun onNothingSelected(parent: AdapterView<*>?) {

      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mPresenter.getSubPos(arrayList[position])
      }

    }
  }

  override fun setSupPos(list: ArrayList<ItemSpinner>) {
    val arrayList: ArrayList<String> = arrayListOf()
    list.forEach {
      arrayList.add(it.item)
    }
    val aa = context?.let { ArrayAdapter(it, layout.simple_spinner_item, arrayList) }
    aa?.setDropDownViewResource(layout.simple_spinner_dropdown_item)
    binding.subDetail.adapter = aa
    binding.subDetail.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
      override fun onNothingSelected(parent: AdapterView<*>?) {

      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mPresenter.getUraian(binding.kategori.selectedItem.toString(), binding.subKategori.selectedItem.toString(), binding.detailKategori.selectedItem.toString(), arrayList[position])
      }

    }
  }


  @SuppressLint("SetTextI18n")
  private fun showCalendar() {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val dpd = context?.let {
      DatePickerDialog(it,
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
          binding.tanggal.text = "" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth
        }, year, month, day)
    }

    dpd?.show()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  override fun onItemClick(item: ItemHome) {
    TODO("Not yet implemented")
  }
}