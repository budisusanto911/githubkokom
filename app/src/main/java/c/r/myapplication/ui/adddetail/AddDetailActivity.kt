package c.r.myapplication.ui.adddetail

import android.R.layout
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import c.r.myapplication.NewsApp
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.data.remote.model.ItemSpinner
import c.r.myapplication.databinding.ActivityAddDetailItemBinding
import c.r.myapplication.ui.base.BaseActivity
import c.r.myapplication.utils.SessionManager
import javax.inject.Inject
import c.r.myapplication.ui.adddetail.AdddetailContract.ContractView
import java.util.ArrayList

class AddDetailActivity : BaseActivity(), ContractView {

  private lateinit var binding: ActivityAddDetailItemBinding

  @Inject
  lateinit var mPresenter: AdddetailPresenter

  private var foto: String = ""
  private lateinit var sessionManager: SessionManager

  init {
    NewsApp.mNewsComponent?.inject(this)
    mPresenter.attachView(this)
  }


  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAddDetailItemBinding.inflate(layoutInflater)
    setContentView(binding.root)
    sessionManager = SessionManager(this)
    sessionManager.init()
    binding.run {
      setSupportActionBar(toolbarView.toolbar)
      toolbarView.tvPageTitle.text = "Add item"

      simpan.setOnClickListener {
        val no = this.kategori.selectedItem.toString() + subKategori.selectedItem.toString() +
            detailKategori.selectedItem.toString() + subDetail.selectedItem.toString()
        val user = sessionManager.getUserDetails()

        val id = intent.getStringExtra("cr_id_hdr")
        val tanggal = intent.getStringExtra("cr_tanggal")
        if (id != null) {
          mPresenter.setSave(no, id, tanggal?:"",nominal.text.toString(), uraian.text.toString(), user?.get(sessionManager.KEY_ID)?: "")
        }
      }
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    mPresenter.getKategori()
  }
  override fun setItem(list: ItemSpinner) {
    binding.keterangan.setText(list.item)
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
  override fun setIdInsert(idInsert: Int) {

    finish()
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

  override fun setKategori(list: ArrayList<ItemSpinner>) {
    val arrayList: ArrayList<String> = arrayListOf()
    list.forEach {
      arrayList.add(it.item)
    }
    val aa = ArrayAdapter(this, layout.simple_spinner_item, arrayList)
    aa.setDropDownViewResource(layout.simple_spinner_dropdown_item)
    binding.kategori.adapter = aa
    binding.kategori.onItemSelectedListener = object : OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {

      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mPresenter.getSubKategori(arrayList[position])
      }

    }
  }
  override fun setSubKategori(list: ArrayList<ItemSpinner>) {
    val arrayList: ArrayList<String> = arrayListOf()
    list.forEach {
      arrayList.add(it.item)
    }
    val aa = ArrayAdapter(this, layout.simple_spinner_item, arrayList)
    aa.setDropDownViewResource(layout.simple_spinner_dropdown_item)
    binding.subKategori.adapter = aa
    binding.subKategori.onItemSelectedListener = object : OnItemSelectedListener {
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
    binding.detailKategori.onItemSelectedListener = object : OnItemSelectedListener {
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
    binding.subDetail.onItemSelectedListener = object : OnItemSelectedListener {
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

}