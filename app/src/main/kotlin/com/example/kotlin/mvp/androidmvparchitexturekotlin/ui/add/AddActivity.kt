package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add

import android.R.layout
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog.Builder
import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.NewsApp
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.ItemSpinner
import com.example.kotlin.mvp.androidmvparchitexturekotlin.databinding.ActivityAddItemBinding
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add.AddContract.ContractView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add.custom_view.CardEditText
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add.custom_view.DatabaseContract.KEY_DESCRIPSI
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add.custom_view.DatabaseContract.KEY_NOMINAL
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add.custom_view.EmojiExcludeFilter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add.custom_view.FormLayout
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.BaseActivity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.utils.SessionManager
import kotlinx.android.synthetic.main.toolbar_view.toolbar
import pl.aprilapps.easyphotopicker.EasyImage
import java.util.ArrayList
import java.util.Calendar
import java.util.HashMap
import javax.inject.Inject

class AddActivity : BaseActivity(), ContractView {

  private lateinit var binding: ActivityAddItemBinding

  @Inject
  lateinit var mPresenter: AddPresenter
  private val property =
    arrayOf(
      arrayOf(KEY_NOMINAL, "Nominal ", "Masukan nominal", "required"),
      arrayOf(KEY_DESCRIPSI, "Deskripsi ", "Masukan deskripsi", "required")
    )
  private val allEditText = HashMap<String, List<CardEditText>>()
  private val REQUEST_CODE_CAMERA = 10
  private val REQUEST_CODE_GALLERY = 11
  private lateinit var sessionManager: SessionManager

  init {
    NewsApp.mNewsComponent?.inject(this)
    mPresenter.attachView(this)
  }


  @SuppressLint("SetTextI18n")
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

      btnTambah.setOnClickListener {
        setupForm()
      }
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    mPresenter.getKategori()
    setupForm()
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
  private fun removeKursus(form: FormLayout) {
    Builder(this)
      .setMessage("Apakah anda yakin akan menghapus detail transaksi ini?")
      .setTitle("Hapus Transaksi")
      .setPositiveButton("Hapus") { dialog, which ->
        val idLayout = form.getTag() as Int
        allEditText.remove(idLayout.toString())
        binding.layKursus.removeView(form)

      }
      .setNegativeButton("Tidak", null)
      .show()
  }

  @SuppressLint("SetTextI18n")
  private fun setupForm() {
    var i=0
    val urut: Int = allEditText.size + 1
    val formLayout = FormLayout(this)
    formLayout.tag = urut
    formLayout.title?.text = "Detail Transaksi"
    formLayout.subtitle?.text = "Silahkan input detail CR"
    formLayout.btnDelete?.visibility = View.GONE
    if (urut != 1) {
      formLayout.btnDelete?.visibility = View.VISIBLE
      formLayout.btnDelete?.setOnClickListener(View.OnClickListener { removeKursus(formLayout) })
    }
    val tmpListEditText: MutableList<CardEditText> = ArrayList<CardEditText>()
    while (i < property.size) {
      val cardEditText = CardEditText(this)
      cardEditText.tag = property[i][0]
      cardEditText.setCaption(property[i][1])
      cardEditText.setHint(property[i][2])
      cardEditText.paddingTop = 20
      cardEditText.paddingRight = 20
      cardEditText.paddingLeft = 20

      if (i==0) cardEditText.setInputType(InputType.TYPE_CLASS_NUMBER)
      else cardEditText.setInputType(InputType.TYPE_CLASS_TEXT)
     // cardEditText.setFilters(arrayOf<InputFilter>(EmojiExcludeFilter()))
      if (i == property.size - 1) {
        cardEditText.paddingBottom = 20
      }
      if (property[i][3] == "required") {
        cardEditText.setRequired(true)
      } else {
        cardEditText.setRequired(false)
      }
      tmpListEditText.add(cardEditText)
      formLayout.layoutMain?.addView(cardEditText)
      i++
    }
    allEditText[urut.toString()] = tmpListEditText
    binding.layKursus.addView(formLayout)
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