package c.r.myapplication.ui.home.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import c.r.myapplication.R
import c.r.myapplication.data.remote.model.ItemHome
import c.r.myapplication.databinding.ItemHomeBinding
import c.r.myapplication.ui.home.item.HomeAdapter.ViewHolder
import c.r.myapplication.utils.toRupiah

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

class HomeAdapter(private var context: Context, private var items: List<ItemHome>, private var listener: OnItemClickListener) :
        RecyclerView.Adapter<ViewHolder>() {
  private lateinit var binding: ItemHomeBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_home, parent, false)
      binding = ItemHomeBinding.bind(v)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.bind(context, items[position], listener)


    }

    override fun getItemCount(): Int = items?.size

    class ViewHolder(binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        val id = binding.id
        val nama = binding.nama
        val jumlah = binding.jumlah
        val tanggal = binding.tanggal

        fun bind(context: Context, item: ItemHome?, listener: OnItemClickListener) {

            if (item != null) {
              id.text = item.cr_id_hdr
              nama.text = item.cr_no_hdr
              jumlah.text = toRupiah(item.total ?: 0.0)
              tanggal.text = item.cr_tanggal

            }

            itemView.setOnClickListener { view -> listener.onItemClick(item!!) }
        }

    }

    fun setItems(items: List<ItemHome>) {
      this.items = items
      notifyDataSetChanged()
    }

    fun getItems(): List<ItemHome> {
      return this.items
    }
    interface OnItemClickListener {

        fun onItemClick(item: ItemHome)

    }
}