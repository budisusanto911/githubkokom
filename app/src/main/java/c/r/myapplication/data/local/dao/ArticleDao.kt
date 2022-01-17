package c.r.myapplication.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import c.r.myapplication.data.local.entities.ArticleEntity
import io.reactivex.Flowable

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getArticlesFromDb() : Flowable<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArticles(users : List<ArticleEntity>)

    @Query("DELETE FROM article")
    fun deleteArticles()

}