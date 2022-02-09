package c.r.myapplication.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import c.r.myapplication.R
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.databinding.ItemNewsBinding
import c.r.myapplication.ui.news.NewsAdapter.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

class NewsAdapter(private var context: Context, private var items: List<ArticleEntity>, private var listener: OnItemClickListener) :
        RecyclerView.Adapter<ViewHolder>() {
  private lateinit var binding: ItemNewsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_news, parent, false)
      binding = ItemNewsBinding.bind(v)
      return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.bind(context, items[position], listener)

    }

    override fun getItemCount(): Int = items?.size

    class ViewHolder(itemView: ItemNewsBinding) : RecyclerView.ViewHolder(itemView.root) {

        val mThumbnail = itemView.thumbnail
        val mTitle = itemView.title
        val mDescription = itemView.description
        val mProgress = itemView.progress

        fun bind(context: Context, item: ArticleEntity?, listener: OnItemClickListener) {

            if (item != null) {

                mTitle.setText(item?.title)

                mDescription.setText(item?.description)

                if (item?.urlToImage != null) {

                    Glide
                            .with(context)
                            .load(item?.urlToImage)
                            .listener(object : RequestListener<String, GlideDrawable> {
                                override fun onException(e: Exception, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                                    if (mProgress != null)
                                        mProgress.visibility = View.GONE

                                    return false
                                }

                                override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean,
                                                             isFirstResource: Boolean): Boolean {

                                    if (mProgress != null)
                                        mProgress.visibility = View.GONE

                                    return false
                                }
                            })
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .crossFade()
                            .into(mThumbnail)

                }else{
                    Glide
                            .with(context)
                            .load(R.drawable.new_placeholder)
                            .into(mThumbnail)

                    mProgress.visibility = View.GONE

                }

            }

            itemView.setOnClickListener { view -> listener.onItemClick(item!!) }
        }

    }

    fun setItems(items: List<ArticleEntity>) {

        if(items != null && !items?.isEmpty()) {

            this.items = items
            notifyDataSetChanged()

        }
    }

    interface OnItemClickListener {

        fun onItemClick(item: ArticleEntity)

    }
}