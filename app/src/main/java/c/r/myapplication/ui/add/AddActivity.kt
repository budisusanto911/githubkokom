package c.r.myapplication.ui.add

import android.Manifest.permission
import android.R.id
import android.R.layout
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import c.r.myapplication.ui.add.AddContract.ContractView
import c.r.myapplication.ui.base.BaseActivity
import io.reactivex.Observable
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import java.util.ArrayList
import java.util.Calendar
import javax.inject.Inject
import java.lang.Exception
import android.view.MenuItem
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.core.content.ContextCompat
import c.r.myapplication.NewsApp
import c.r.myapplication.R
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.data.remote.model.ItemSpinner
import c.r.myapplication.databinding.ActivityAddItemBinding
import c.r.myapplication.ui.detail.DetailActivity
import c.r.myapplication.utils.SessionManager
import com.bumptech.glide.Glide
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource

class AddActivity : BaseActivity(), ContractView {

  private lateinit var binding: ActivityAddItemBinding

  private var easyImage: EasyImage? = null

  @Inject
  lateinit var mPresenter: AddPresenter

  private var foto: String = ""
  private var idInsert: Int = 0
  var fileImageUri: Uri? = null
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
        camera.setOnClickListener { easyImage?.openCameraForImage(this@AddActivity)
      }
      galery.setOnClickListener {
        easyImage?.openGallery(this@AddActivity)
      }
      tanggal.setOnClickListener {
        showCalendar()
      }
      simpan.setOnClickListener {

        mPresenter.setSave(binding.nomorCR.text.toString(), foto, tanggal.text.toString())
      }
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)

    val permissionCheck =
      ContextCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE)
    easyImage = EasyImage.Builder(this)
      .build()

  }

  override fun getImageCompressor(): Observable<File> {
    return Helper.getImageCompressor(this, File(fileImageUri?.path))
  }



  override fun setIdInsert(idInsert: Int) {
    this.idInsert = idInsert
    val intent = Intent(this, DetailActivity::class.java)
    intent.putExtra("cr_id_hdr", idInsert.toString())
    startActivity(intent)
  }
  override fun setLink(link: String, name: String) {
    Glide.with(this@AddActivity)
      .load(link)
      .error(R.drawable.icon)
      .into(binding.ivFile)

    this.foto = name
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      id.home->   {
        onBackPressed()
        return true
      }
      // Action goes here
      else -> super.onOptionsItemSelected(item)
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
    easyImage?.handleActivityResult(
      requestCode, resultCode, data, this, object : DefaultCallback() {

        override fun onMediaFilesPicked(
          imageFiles: Array<MediaFile>,
          source: MediaSource
        ) {
          fileImageUri = Uri.fromFile(imageFiles[0].file)
          mPresenter.uploadImage(1)
        }

        override fun onImagePickerError(
          error: Throwable,
          source: MediaSource
        ) {
          Toast.makeText(this@AddActivity, error.localizedMessage, Toast.LENGTH_SHORT).show()
        }

        override fun onCanceled(source: MediaSource) {
          // presenter.onImagePickCancel()
        }
      })
    /*EasyImage.handleActivityResult(requestCode,
      resultCode,
      data,
      this,
      object : DefaultCallback() {
        override fun onImagePickerError(e: Exception, source: ImageSource, type: Int) {
          //Some error handling
          e.printStackTrace()
        }

        override fun onImagesPicked(imageFiles: List<File>, source: ImageSource, type: Int) {
          fileImageUri = Uri.parse(imageFiles[0].path)
          mPresenter.uploadImage(1)
        }

        override fun onCanceled(source: ImageSource, type: Int) {
          //Cancel handling, you might wanna remove taken photo if it was canceled
          if (source == CAMERA_IMAGE) {
            val photoFile = EasyImage.lastlyTakenButCanceledPhoto(this@AddActivity)
            photoFile?.delete()
          }
        }
      })
*/
  }
}