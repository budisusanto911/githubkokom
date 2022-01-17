package c.r.myapplication.ui.add

import android.content.Context
import android.graphics.Bitmap
import id.zelory.compressor.Compressor
import io.reactivex.Flowable
import io.reactivex.Observable
import java.io.File

object Helper {
  fun getImageCompressor(
    context: Context,
    file: File
  ): Observable<File> {
    return Compressor(context)
      .setQuality(80)
      .setCompressFormat(Bitmap.CompressFormat.JPEG)
      .compressToFileAsFlowable(file).toObservable()
  }
}