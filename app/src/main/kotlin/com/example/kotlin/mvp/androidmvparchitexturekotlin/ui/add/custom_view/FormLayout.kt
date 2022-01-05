package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.R
import com.example.kotlin.mvp.androidmvparchitexturekotlin.databinding.IoFormLayoutBinding

class FormLayout : LinearLayout {
  var view: IoFormLayoutBinding? = null
  var title: TextView? = null
  var subtitle: TextView? = null
  var btnDelete: ImageButton? = null
  var layoutMain: LinearLayout? = null

  constructor(context: Context?) : super(context) {
    init()
  }

  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
    init()
  }

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
    attrs,
    defStyleAttr) {
    init()
  }

  private fun init() {
    view = IoFormLayoutBinding.bind(inflate(context, R.layout.io_form_layout, this))
    title = view?.txtTitle as TextView
    btnDelete = view?.btnDelete as ImageButton
    subtitle = view?.txtSubtitle as TextView
    layoutMain = view?.layMain as LinearLayout
  }
}
