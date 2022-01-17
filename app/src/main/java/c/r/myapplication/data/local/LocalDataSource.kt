package c.r.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import c.r.myapplication.data.local.dao.ArticleDao
import c.r.myapplication.data.local.entities.ArticleEntity
import javax.inject.Singleton

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

@Singleton
@Database(entities = arrayOf(ArticleEntity::class), version = 1)
abstract class LocalDataSource : RoomDatabase() {
   // abstract fun getArticleDao() : ArticleDao
}