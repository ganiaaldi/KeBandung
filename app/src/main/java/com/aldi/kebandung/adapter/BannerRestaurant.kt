package com.aldi.kebandung.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aldi.kebandung.R
import com.aldi.kebandung.etc.Endpoint
import com.aldi.kebandung.model.Restaurant
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BannerRestaurant (val listVacation: ArrayList<Restaurant>) : RecyclerView.Adapter<BannerRestaurant.ListViewHolder> () {
    private val limit = 4

    override fun getItemCount(): Int {
        if(listVacation.size > limit){
            return limit;
        }
        else
        {
            return listVacation.size;
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Restaurant)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgVacation: ImageView = itemView.findViewById(R.id.imageViewVacation)
        var namaVacation: TextView = itemView.findViewById(R.id.tvNameVacation)
        var lokasiVacation: TextView = itemView.findViewById(R.id.tvLokasiVacation)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_banner, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val rekomendasi = listVacation[position]
        val url = Endpoint.GAMBAR+rekomendasi.gambarRestaurant
        Glide.with(holder.itemView.context)
            .load(url)
            .apply(RequestOptions())
            .into(holder.imgVacation)
        holder.namaVacation.text = rekomendasi.namaRestaurant
        holder.lokasiVacation.text = rekomendasi.namaDaerah
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listVacation[holder.adapterPosition])
        }
    }
}