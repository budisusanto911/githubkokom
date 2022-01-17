package c.r.myapplication.data.remote.model

import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Serializer @Inject constructor() {

  private val gson = Gson()

  fun serialize(
    `object`: Any,
    clazz: Class<*>
  ): String {
    return gson.toJson(`object`, clazz)
  }

  fun <T> deserialize(
    string: String,
    clazz: Class<T>
  ): T {
    return gson.fromJson(string, clazz)
  }
}