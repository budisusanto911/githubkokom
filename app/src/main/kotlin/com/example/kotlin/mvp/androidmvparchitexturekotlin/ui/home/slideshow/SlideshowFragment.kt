package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.slideshow

import android.R.layout
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.ItemSpinner
import com.example.kotlin.mvp.androidmvparchitexturekotlin.databinding.FragmentSlideshowBinding
import java.util.ArrayList
import java.util.Calendar

class SlideshowFragment : Fragment() {

  private var _binding: FragmentSlideshowBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {

    _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
    val root: View = binding.root

  /*  val textView: TextView = binding.textSlideshow
    slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })*/
    return root
  }


  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}