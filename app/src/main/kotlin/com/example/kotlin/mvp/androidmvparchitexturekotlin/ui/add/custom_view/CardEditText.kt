package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add.custom_view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.R
import com.example.kotlin.mvp.androidmvparchitexturekotlin.databinding.IoCardedittextBinding

class CardEditText(context: Context?) : LinearLayout(context) {
  private var view: IoCardedittextBinding? = null
  private var label: TextView? = null
  private var editText: EditText? = null
  private var hint: CharSequence? = null
  private var caption:CharSequence? = null
  private var tag: Any? = null
  private var padding =0
  private var paddingTop:Int = 0
  private var paddingBottom:Int = 0
  private var paddingLeft:Int = 0
  private var paddingRight:Int = 0
  private var inputType = 0
  private var required = false
  private var singleLine:Boolean = false
  private var maxLength = 0
  private var minLength = 0


  private fun setLabelRequired() {
    val text = SpannableString(caption.toString() + " *")
    text.setSpan(ForegroundColorSpan(Color.RED), text.length - 1, text.length, 0)
    label!!.text = text
  }

  fun CardEditText(context: Context?) {
    init()
  }

  fun setTextArea(`val`: Boolean) {
    singleLine = `val`
    editText!!.isSingleLine = !singleLine
    if (!singleLine) {
      editText!!.maxLines = 5
      editText!!.gravity = Gravity.LEFT or Gravity.TOP
      editText!!.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
      editText!!.isHorizontalScrollBarEnabled = false
    } else {
      editText!!.maxLines = 1
    }
  }

  fun setMaxLength(length: Int) {
    maxLength = length
    editText!!.filters = arrayOf<InputFilter>(LengthFilter(maxLength))
  }

  fun getMaxLength(): Int {
    return maxLength
  }

  fun setMinLength(length: Int) {
    minLength = length
  }

  fun getMinLength(): Int {
    return minLength
  }

  fun getTextArea(): Boolean {
    return singleLine
  }

  fun length(): Int {
    return editText!!.length()
  }

  fun getHint(): CharSequence? {
    return hint
  }

  fun setHint(hint: CharSequence?) {
    this.hint = hint
    editText!!.hint = hint
  }

  fun getCaption(): CharSequence? {
    return caption
  }

  fun setCaption(caption: CharSequence?) {
    this.caption = caption
    if (isRequired()) {
      setLabelRequired()
    } else {
      label!!.text = caption
    }
  }

  fun setError(error: CharSequence?) {
    editText!!.error = error
  }

  fun setError(error: CharSequence?, icon: Drawable?) {
    editText!!.setError(error, icon)
  }

  fun getText(): String? {
    return editText!!.text.toString()
  }

  fun setText(text: CharSequence?) {
    editText!!.setText(text)
  }

  override fun getTag(): Any? {
    return tag
  }

  override fun setTag(tag: Any?) {
    this.tag = tag
    editText!!.tag = tag
  }

  fun getPadding(): Int {
    return padding
  }

  fun setPadding(padding: Int) {
    this.padding = padding
    this.setPadding(padding, padding, padding, padding)
  }

  override fun getPaddingTop(): Int {
    return paddingTop
  }


  fun setPaddingTop(paddingTop: Int) {
    this.paddingTop = paddingTop
    this.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
  }

  override fun getPaddingBottom(): Int {
    return paddingBottom
  }

  fun setPaddingBottom(paddingBottom: Int) {
    this.paddingBottom = paddingBottom
    this.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
  }

  override fun getPaddingLeft(): Int {
    return paddingLeft
  }

  fun setPaddingLeft(paddingLeft: Int) {
    this.paddingLeft = paddingLeft
    this.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
  }

  override fun getPaddingRight(): Int {
    return paddingRight
  }

  fun setPaddingRight(paddingRight: Int) {
    this.paddingRight = paddingRight
    this.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
  }

  fun isRequired(): Boolean {
    return required
  }

  fun setRequired(required: Boolean) {
    this.required = required
    if (required) {
      setLabelRequired()
    } else {
      label!!.text = caption
    }
  }

  fun setFilters(inputFilters: Array<InputFilter>?) {
    editText!!.filters = inputFilters
  }

  fun getInputType(): Int {
    return inputType
  }

  fun setInputType(inputType: Int) {
    this.inputType = inputType
    editText!!.inputType = inputType
  }

  private fun init() {
    view = IoCardedittextBinding.bind(inflate(context, R.layout.io_cardedittext, this))
    label = view?.label
    editText = view?.edtText
    editText!!.isSingleLine = singleLine
    //editText!!.filters = arrayOf(EmojiExcludeFilter())
  }

  init {
    init()
  }
}
