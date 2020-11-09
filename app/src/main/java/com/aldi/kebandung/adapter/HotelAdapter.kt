package com.aldi.kebandung.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aldi.kebandung.Endpoint
import com.aldi.kebandung.R
import com.aldi.kebandung.model.Hotel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class HotelAdapter(val listVacation: ArrayList<Hotel>) : RecyclerView.Adapter<HotelAdapter.ListViewHolder> () {
    override fun getItemCount(): Int {
        return listVacation.size
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Hotel)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgHotel: ImageView = itemView.findViewById(R.id.imageViewVacation)
        var nameHotel: TextView = itemView.findViewById(R.id.tvNameVacation)
        var locateHotel: TextView = itemView.findViewById(R.id.tvLokasiVacation)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_content, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val hotel = listVacation[position]
        val url = Endpoint.GAMBAR+hotel.gambarHotel
        Glide.with(holder.itemView.context)
            .load(url)
            .apply(RequestOptions())
            .into(holder.imgHotel)
        holder.nameHotel.text = hotel.namaHotel
        holder.locateHotel.text = hotel.namaDaerah
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listVacation[holder.adapterPosition])
        }
    }
}