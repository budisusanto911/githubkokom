package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add

import android.R.layout
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.NewsApp
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.ItemSpinner
import com.example.kotlin.mvp.androidmvparchitexturekotlin.databinding.ActivityAddItemBinding
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add.AddContract.ContractView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.BaseActivity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.utils.SessionManager
import com.google.gson.Gson
import pl.aprilapps.easyphotopicker.EasyImage
import java.util.ArrayList
import java.util.Calendar
import javax.inject.Inject

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

class AddActivity : BaseActivity(), ContractView {

  private lateinit var binding: ActivityAddItemBinding

  @Inject
  lateinit var mPresenter: AddPresenter

  private var easyImage: EasyImage? = null
  val REQUEST_CODE_CAMERA = 10
  val REQUEST_CODE_GALLERY = 11
  private lateinit var sessionManager: SessionManager

  init {
    NewsApp.mNewsComponent?.inject(this)
    mPresenter.attachView(this)
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAddItemBinding.inflate(layoutInflater)
    setContentView(binding.root)
    sessionManager = SessionManager(this)
    sessionManager.init()
    binding.run {
      setSupportActionBar(toolbarView.toolbar)
      toolbarView.tvPageTitle.text = "Add item"
      camera.setOnClickListener {
        EasyImage.openCameraForImage(this@AddActivity, REQUEST_CODE_CAMERA)
      }
      galery.setOnClickListener {
        EasyImage.openGallery(this@AddActivity, REQUEST_CODE_GALLERY)
      }
      tanggal.setOnClickListener {
        showCalendar()
      }

    }
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    mPresenter.getKategori()

  }

  override fun setKategori(list: ArrayList<ItemSpinner>) {
    val arrayList: ArrayList<String> = arrayListOf()
    list.forEach {
      arrayList.add(it.item)
    }
    val aa = ArrayAdapter(this, layout.simple_spinner_item, arrayList)
    aa.setDropDownViewResource(layout.simple_spinner_dropdown_item)
    binding.kategori.adapter = aa
    binding.kategori.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {

      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mPresenter.getSubKategori(arrayList[position])
      }

    }
  }

  override fun setItem(list: ItemSpinner) {
    binding.uraian.text = list.item
  }

  override fun setSubKategori(list: ArrayList<ItemSpinner>) {
    val arrayList: ArrayList<String> = arrayListOf()
    list.forEach {
      arrayList.add(it.item)
    }
    val aa = ArrayAdapter(this, layout.simple_spinner_item, arrayList)
    aa.setDropDownViewResource(layout.simple_spinner_dropdown_item)
    binding.subKategori.adapter = aa
    binding.subKategori.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
    val aa = ArrayAdapter(this, layout.simple_spinner_item, arrayList)
    aa.setDropDownViewResource(layout.simple_spinner_dropdown_item)
    binding.detailKategori.adapter = aa
    binding.detailKategori.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
    val aa = ArrayAdapter(this, layout.simple_spinner_item, arrayList)
    aa.setDropDownViewResource(layout.simple_spinner_dropdown_item)
    binding.subDetail.adapter = aa
    binding.subDetail.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {

      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mPresenter.getUraian(binding.kategori.selectedItem.toString(),
          binding.subKategori.selectedItem.toString(),
          binding.detailKategori.selectedItem.toString(),
          arrayList[position])
      }

    }
  }


  @SuppressLint("SetTextI18n")
  private fun showCalendar() {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val dpd = DatePickerDialog(this,
      DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        binding.tanggal.text = "" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth
      }, year, month, day)

    dpd.show()
  }

  override fun setUp() {


  }

  override fun onArtilesReady(items: List<ArticleEntity>) {

    if (items.isNotEmpty()) {

      hideErrorContainer()

    } else {

      showErrorContainer()
    }

  }


  private fun showErrorContainer() {


  }

  private fun hideErrorContainer() {


  }


  override fun onDestroy() {

    mPresenter.detachView()
    super.onDestroy()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

  }
}