package c.r.myapplication.utils

import android.annotation.SuppressLint
import android.widget.TextView
import java.io.UnsupportedEncodingException
import java.math.RoundingMode
import java.math.RoundingMode.HALF_DOWN
import java.net.URLEncoder
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.HashMap
import java.util.Locale
import java.util.TimeZone
import java.util.regex.Pattern
import kotlin.math.ceil

private const val SECOND_MILLIS = 1000
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS

fun toRupiah(num: Long): String {
  val dfs = DecimalFormatSymbols()
  dfs.groupingSeparator = '.'
  val df = DecimalFormat()
  df.decimalFormatSymbols = dfs
  return "Rp ${df.format(num)}"
}

fun toRupiah(num: Double): String {
  val dfs = DecimalFormatSymbols()
  dfs.groupingSeparator = '.'
  val df = DecimalFormat()
  df.decimalFormatSymbols = dfs

  return "Rp ${df.format(ceil(num))}"
}

fun toRupiahNoSpace(num: Double): String {
  val dfs = DecimalFormatSymbols()
  dfs.groupingSeparator = '.'
  val df = DecimalFormat()
  df.decimalFormatSymbols = dfs

  return "Rp${df.format(ceil(num))}"
}

fun toRupiahNoSpace(num: Long): String {
  val dfs = DecimalFormatSymbols()
  dfs.groupingSeparator = '.'
  val df = DecimalFormat()
  df.decimalFormatSymbols = dfs
  return "Rp${df.format(num)}"
}

fun Double.formatPercent(digits: Int): String = "%.${digits}f%%".format(this)

fun Double.getPercent(): Double {
  return try {
    val df = DecimalFormat("#")
    df.roundingMode = HALF_DOWN
    df.format(this).toDouble()
  } catch (e: NumberFormatException) {
    0.0
  }
}

fun formatNumber(num: Long): String {
  val dfs = DecimalFormatSymbols()
  dfs.groupingSeparator = '.'
  val df = DecimalFormat()
  df.decimalFormatSymbols = dfs
  return df.format(num)
}

fun formatNumber(num: Int): String {
  return formatNumber(num.toLong())
}

fun formatToDate(
  millisecond: Long,
  format: String,
  timeZone: TimeZone? = TimeZone.getDefault(),
  replaceToday: Boolean = false,
  language: String = "in"
): String {
  val date = if (replaceToday && millisecond == 0.toLong()) Date()
  else Date(millisecond)

  val formatter = SimpleDateFormat(format, Locale(language))
  formatter.timeZone = timeZone
  return formatter.format(date)
}

fun formatToDate(
  date: Date,
  format: String? = Const.SERVER_DATE_FORMAT,
  timeZone: TimeZone? = TimeZone.getDefault()
): String {
  val formatter = SimpleDateFormat(format, Locale("in"))
  formatter.timeZone = timeZone
  return formatter.format(date)
}

fun formatToDate(
  dateInString: String,
  format: String,
  timeZone: TimeZone? = TimeZone.getDefault()
): Date {
  val formatter = SimpleDateFormat(format, Locale("in"))
  formatter.timeZone = timeZone
  try {
    return formatter.parse(dateInString)
  } catch (e: ParseException) {
    e.printStackTrace()
  }

  return Date()
}

fun getDifferenceDays(d1: Date, d2: Date): Int {
  var daysdiff = 0
  val diff = d2.time - d1.time
  val diffDays = diff / (24 * 60 * 60 * 1000) + 1
  daysdiff = diffDays.toInt()
  return daysdiff
}

fun formatToMilliseconds(
  dateInString: String,
  format: String,
  timeZone: TimeZone? = TimeZone.getDefault(),
  language: String = "in"
): Long {
  val formatter = SimpleDateFormat(format, Locale(language))
  formatter.timeZone = timeZone
  try {
    val date = formatter.parse(dateInString)
    return date.time
  } catch (e: ParseException) {
    e.printStackTrace()
  }

  return 0
}

fun formatDate(
  dateInString: String,
  format: String,
  timeZone: TimeZone? = TimeZone.getDefault()
): String {
  val dateInMillis = formatToMilliseconds(dateInString, Const.SERVER_DATE_FORMAT, timeZone)
  return formatToDate(dateInMillis, format, timeZone)
}

fun formatDate(
  dateInString: String,
  format: String,
  toFormat: String,
  timeZone: TimeZone? = TimeZone.getDefault(),
  replaceToday: Boolean = false,
  language: String = "in"
): String {
  val date = if (replaceToday && dateInString.isEmpty()) {
    val formatter = SimpleDateFormat(format, Locale(language))
    formatter.timeZone = timeZone
    formatter.format(Date())
  } else {
    dateInString
  }
  val dateInMillis = formatToMilliseconds(date, format, timeZone, language)
  return formatToDate(dateInMillis, toFormat, timeZone, language = language)
}

fun millisToDateString(
  date: Long,
  format: String,
  toFormat: String,
  timeZone: TimeZone? = TimeZone.getDefault()
): String {
  if (date == Long.MIN_VALUE) return "-"
  val dateFormat: DateFormat = DateFormat.getDateInstance()
  return dateFormat.format(Date(date))
}

fun getCurrenTimeMilis(): Long {
  return System.currentTimeMillis()
}

@SuppressLint("SetTextI18n")
fun TextView.setToTimeAgo(
  dateTime: String,
  dateFormat: String
) {
  var time = formatToMilliseconds(dateTime, dateFormat)
  if (time < 1000000000000L) {
    time *= 1000
  }

  val now = System.currentTimeMillis()
  if (time > now || time <= 0) {
    this.text = "A Moments Ago"
  }

  val diff = now - time
  when {
    diff < MINUTE_MILLIS -> this.text = "now"
    diff < 2 * MINUTE_MILLIS -> this.text = "a minute ago"
    diff < 50 * MINUTE_MILLIS -> this.text = (diff / MINUTE_MILLIS).toString() + " minutes ago"
    diff < 90 * MINUTE_MILLIS -> this.text = "a hour ago"
    diff < 24 * HOUR_MILLIS -> this.text = (diff / HOUR_MILLIS).toString() + " hours ago"
    diff < 48 * HOUR_MILLIS -> this.text = "Yesterday"
    else -> {
      val dayDiff = (diff / DAY_MILLIS).toInt()
      this.text = if (dayDiff < 31)
        "$dayDiff days ago"
      else {
        val monthDiff = dayDiff / 30
        if (monthDiff < 12) {
          "$monthDiff months ago"
        } else {
          formatToDate(time, "dd MMM yyyy")
        }
      }
    }
  }
}

fun String.isEmailValid(): Boolean {
  return if (this.isNotEmpty()) {
    Pattern.compile(
      "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
          + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
          + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
          + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
          + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
          + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
    )
      .matcher(this)
      .matches()
  } else {
    false
  }
}

fun String.isCharacterOnly(): Boolean {
  return if (this.isNotEmpty()) {
    Pattern.compile("[a-zA-Z ]+")
      .matcher(this)
      .matches()
  } else {
    false
  }
}

fun String.isLengthNotMatch(length: Int): Boolean {
  return if (this.isNotEmpty()) {
    this.length < length
  } else {
    false
  }
}

fun Double.format(digits: Int): String = String.format("%.${digits}f", this)

fun HashMap<String, String>.formatToString(): String {
  val stringBuilder = StringBuilder()

  for (key in this.keys) {
    if (stringBuilder.isNotEmpty()) {
      stringBuilder.append("&")
    }
    val value = this[key]
    try {
      stringBuilder.append(URLEncoder.encode(key, "UTF-8"))
      stringBuilder.append("=")
      stringBuilder.append(if (value != null) URLEncoder.encode(value, "UTF-8") else "")
    } catch (e: UnsupportedEncodingException) {
      throw RuntimeException("This method requires UTF-8 encoding support", e)
    }
  }

  return stringBuilder.toString()
}

fun String.capital(): String {
  val splits = this.lowercase(Locale.ROOT).split(" ").toTypedArray()
  val sb = java.lang.StringBuilder()
  for (i in splits.indices) {
    val eachWord = splits[i]
    if (i > 0 && eachWord.isNotEmpty()) {
      sb.append(" ")
    }
    val cap = (eachWord.substring(0, 1)
      .uppercase(Locale.ROOT)
        + eachWord.substring(1))
    sb.append(cap)
  }
  return sb.toString()
}

fun String.capitalizeAddress(): String {
  val locale = Locale.ROOT
  val split = this.lowercase(locale).split(" ")
  return split.joinToString(" ") {
    when {
      it.contains("(") && it.contains(")") -> {
        val subs = it.substring(it.indexOf("(") + 1, it.indexOf(")"))
        String.format(
          "(%s)", if (subs.length > 3) {
            subs.capitalize(locale)
          } else {
            subs.uppercase(locale)
          }
        )
      }
      it.contains("(") -> {
        val subs = it.replace("(", "")
        subs.capitalize(locale)
        String.format("(%s", subs.capitalize(locale))
      }
      it.contains(")") -> {
        val subs = it.replace(")", "")
        String.format("%s)", subs.capitalize(locale))
      }
      it == "dki" || it == "di" -> {
        it.uppercase(locale)
      }
      else -> {
        it.capitalize(locale)
      }
    }
  }
}

fun checkWIBTime(time: String): String {
  if (time.isNotEmpty()) {
    when (time.substring(time.length - 5)) {
      "+0700" -> return "WIB"
      "+0800" -> return "WITA"
      "+0900" -> return "WIT"
    }
    return ""
  } else {
    return "WIB"
  }
}

fun handleLanguageDate(
  date: String,
  toFormat: String
): String {
  return formatDate(date, Const.SERVER_DATE_FORMAT_ALT, toFormat)
}

fun roundOffDecimal(number: Double): Double {
  return number.toBigDecimal()
    .setScale(1, RoundingMode.UP)
    .toDouble()
}

fun String.encode(): String {
  return URLEncoder.encode(this, "UTF-8")
}

fun getFullDayDateString(date: String): String {
  if (date.isEmpty()) return ""
  val calendar = Calendar.getInstance()
  calendar.time = formatToDate(date, Const.YYYY_MM_DD_DATE_FORMAT)
  val dfs = DateFormatSymbols(Locale("in"))
  val day = dfs.weekdays[calendar[Calendar.DAY_OF_WEEK]]
  val date = calendar[Calendar.DATE].toString()
  val mon = dfs.months[calendar[Calendar.MONTH]]
  val year = calendar[Calendar.YEAR]
  return "$day, $date $mon $year"
}