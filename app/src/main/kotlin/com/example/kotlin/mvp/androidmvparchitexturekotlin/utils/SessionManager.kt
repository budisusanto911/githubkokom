package com.example.kotlin.mvp.androidmvparchitexturekotlin.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor


class SessionManager(context: Context?) {
  var pref: SharedPreferences? = null
  var editor: Editor? = null
  private var _context = context

  // Shared pref mode
  private var PRIVATE_MODE = 0

  // Sharedpref file name
  private val PREF_NAME = "TNGLIVEPREF"

  // All Shared Preferences Keys
  private val IS_LOGIN = "IsLoggedIn"

  // User name (make variable public to access from outside)
  val KEY_USER = "user" //email / username

  val KEY_ID = "id"
  val KEY_PSWD = "password"
  val KEY_FOTO = "foto"
  val KEY_NAMA = "nama"
  val KEY_EMAIL = "email"


  // Constructor
  fun init() {
    pref = _context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    editor = pref!!.edit()
  }

  /**
   * Create login session
   */
  fun createLoginSession(id: String?,user: String?, pswd: String?,nama: String?, avatar: String?) {
    editor?.run {
      putBoolean(IS_LOGIN, true)
      putString(KEY_ID, id)
      putString(KEY_USER, user)
      putString(KEY_PSWD, pswd)
      putString(KEY_NAMA, nama)
      putString(KEY_FOTO, avatar)
      editor!!.commit()
    }
  }


  fun setNama(nama: String?) {
    editor!!.putString(KEY_NAMA, nama)
    editor!!.commit()
  }

  fun setNIK(nik: String?) {
    editor!!.putString(KEY_FOTO, nik)
    editor!!.commit()
  }

  fun setPassword(password: String?) {
    editor!!.putString(KEY_PSWD, password)
    editor!!.commit()
  }

  fun setEmail(email: String?) {
    editor!!.putString(KEY_EMAIL, email)
    editor!!.commit()
  }



  /**
   * Get stored session data
   */
  fun getUserDetails(): HashMap<String, String?>? {
    val user = HashMap<String, String?>()
    user[KEY_USER] = pref!!.getString(KEY_USER, null)
    user[KEY_NAMA] = pref!!.getString(KEY_NAMA, null)
    user[KEY_PSWD] = pref!!.getString(KEY_PSWD, null)
    user[KEY_FOTO] = pref!!.getString(KEY_FOTO, null)
    return user
  }

  fun clearData() {
    val user = HashMap<String, String?>()
    user[KEY_USER] = pref!!.getString(KEY_USER, null)
    val username = user[KEY_USER]
    editor?.run {
      clear()
      commit()
      putBoolean(IS_LOGIN, false)
      putString(KEY_USER, "")
      putString(KEY_PSWD, "")
      putString(KEY_FOTO, "")
      putString(KEY_NAMA, "")
      putString(KEY_EMAIL, "")
      commit()
    }
  }

  fun isLoggedIn(): Boolean {
    return pref?.getBoolean(IS_LOGIN, false) ?: false
  }
}