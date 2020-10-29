package com.aldi.kebandung.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aldi.kebandung.R
import com.aldi.kebandung.model.Comment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CommentAdapter (val listComment : ArrayList<Comment>):RecyclerView.Adapter<CommentAdapter.ListViewHolder> ()
{
    override fun getItemCount(): Int {
        return listComment.size
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Comment)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgProfile: ImageView = itemView.findViewById(R.id.imageViewProfile)
        var namaProfile: TextView = itemView.findViewById(R.id.tvNamaAkun)
        var komentarProfile: TextView = itemView.findViewById(R.id.tvKomentar)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_comment, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val bindcomment = listComment[position]
        Glide.with(holder.itemView.context)
            .load(bindcomment.photoAkun)
            .apply(RequestOptions())
            .into(holder.imgProfile)
        holder.namaProfile.text = bindcomment.namaAkun
        holder.komentarProfile.text = bindcomment.komentarAkun
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listComment[holder.adapterPosition])
        }
    }
}