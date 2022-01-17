package c.r.myapplication.utils

/*
 * Copyright (c) 2018. Walden Global Service - All Rights Reserved
 *
 * Created by egiadtya on 28/7/2018
 * egi.aditya@wgs.co.id / egiadtya@gmail.com
 */

object Const {

  const val SERVER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
  const val SERVER_DATE_FORMAT_ALT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
  const val SERVER_DATE_FORMAT_ALT_2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

  const val MMM_DD_YYYY_DATE_FORMAT = "MMM dd, yyyy"
  const val DD_MMM_YYYY_DATE_FORMAT = "dd MMM yyyy"
  const val DD_MMMM_YYYY_DATE_FORMAT = "dd MMMM yyyy"
  const val YYYY_MM_DD_DATE_FORMAT = "yyyy-MM-dd"
  const val EEEE_DD_MMM_DATE_FORMAT = "EEEE, dd MMM"
  const val DD_MMM_YYYY_HH_MM_A_FORMAT = "dd MMM yyyy - HH:mm a"
  const val DEFAULT_PAGE = 0
  const val NOT_DEFINED_VALUE = "NOT_DEFINED_VALUE"
  const val NOT_DEFINED_PRODUCT_LIST_TYPE = "NOT_DEFINED_PRODUCT_LIST_TYPE"
  const val EEEE_DD_MMMM_YYYY_DATE_FORMAT = "EEEE, dd MMMM yyyy, HH:mm a"
  const val EEEE_DD_MMMM_YYYY_DATE_FORMAT_24 = "EEEE, dd MMMM yyyy, HH:mm"
  const val EEEE_DD_MMM_YYYY_FORMAT = "EEEE, dd MMM yyyy"
  const val DD_MMM_YYYY_HH_MM_FORMAT = "dd MMM yyyy - HH:mm"
  const val EEEE_DD_MMM_YYYY_HH_MM_FORMAT = "EEEE, dd MMM yyyy, HH:mm"

  const val REMOTE_DATASOURCE = "REMOTE_DATASOURCE"
  const val LOCAL_DATASOURCE = "LOCAL_DATASOURCE"

  const val NOT_IMPLEMENT_LOCAL_DATASOURCE = "NOT_IMPLEMENT_LOCAL_DATASOURCE"
  const val NOT_IMPLEMENT_REMOTE_DATASOURCE = "NOT_IMPLEMENT_REMOTE_DATASOURCE"
  const val NOT_DEFINED_REQUEST_POSITION = "NOT_DEFINED_REQUEST_POSITION"

  const val REGISTER_PROFILE_IMAGE_EMPTY = "REGISTER_PROFILE_IMAGE_EMPTY"

  const val MEDIA_TYPE_MULTI_PART = "multipart/form-data"
  const val MEDIA_TYPE_TEXT_PLAIN = "text/plain"
  const val FIRST_INDEX: Int = 0

  const val BASE_IMAGE = "base"
  const val SMALL_IMAGE = "small"
  const val THUMBNAIL = "thumb"
  const val MEDIA_GALLERY = "media"

  const val ROTATION_ZERO_DEGREE = 0f
  const val ROTATION_180_DEGREE = 180f

  const val HOME_WIDGET_STATE_SHOPPING = "start_shopping"
  const val HOME_WIDGET_STATE_CHECKOUT = "check_out"
  const val HOME_WIDGET_STATE_PICKUP = "pickup_now"

  const val MINIMUM_TRANSACTION: Long = 10000
  const val ALFASTAMP_UPCOMING_PAGE =
    "alfagift://static_page?id=5d70804b653d3b074630a815&name=alfastamp_is_coming"
  const val ALFASTAR_UPCOMING_PAGE =
    "alfagift://static_page?id=5dce50a1fc0e6f06e6311703&name=alfastar_landing_page"
  const val GOJEK_APP: String = "com.gojek.app"
  const val FORBIDDEN_HTTP_CODE = 403
  const val UNAUTHORIZED_HTTP_CODE = 401

  const val STORE_LOCATOR_RADIUS = 5000 //in meter = 5km
  const val GOPAY_SCHEME = "gojek://gopay"
  const val SHOPEEPAY_SCHEME = "shopeeid"

  const val DIMII_APP = "id.dimii.mobile"
  const val VIRGO_APP = "id.capitalnet.finance.stg"

  const val SHOPPING_ATC = "shopping"
  const val CHECKOUT_ATC = "checkout"

  const val MOVE_DEFAULT_TIME: Long = 300
  const val FADE_DEFAULT_TIME: Long = 300

  const val NAVIGATION_CATEGORY: Int = 120

  const val FLAG_SHOW_SHOPPING_LIST_HEADER_VIEW = 1

  const val VIEW_ALL = "ALL"

  const val VOUCHER_MENU_MAX_HOT_OFFER_SIZE = 5
  const val VOUCHER_MENU_MAX_MY_VOUCHER_SIZE = 5
  const val VOUCHER_MENU_MAX_SUBSCRIPTION_SIZE = 5

  const val DEFAULT_STORE_CITY = "Jabodetabek"
  const val DEFAULT_FCCODE = "eyJzZWxsZXJfaWQiOiIxIiwiZmNfY29kZSI6IlRaMDEifQ=="

  const val DEFAULT_STORES = "BHAYANGKARA"
  const val DEFAULT_STORE_CODE = "AB67"
  const val DEFAULT_ENCODED_STORE_CODE =
    "eyJzYXBhIjp0cnVlLCJkZWxpdmVyeSI6dHJ1ZSwic3RvcmVfY29kZSI6IkFCNjcifQ=="

  fun generateAvatarFileName(): String {
    return "Android-${System.currentTimeMillis()}.jpg"
  }

  const val BASKET_LIMIT_COUNT = 30

  const val SORT_DIRECTION_KEY = "sortDirection"

  const val SORT_FIELD_KEY = "sortField"

  const val OFFICIAL_STORE_LIMIT = 8
  const val OFFICIAL_STORE_SCOPES = "officialstore"

  const val VIRGO_CALL_CENTER = "02130000777"
  const val VIRGO_REDIRECT_URI = "alfagift://virgo-linkage-redirect"
}