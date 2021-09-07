package com.eja.musicplayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eja.musicplayer.R
import com.eja.musicplayer.model.ModelListLagu
import kotlinx.android.synthetic.main.item_list_music.view.*

class MainAdapter(private var context: Context, private var items: List<ModelListLagu>, private var xSelectData: onSelectData) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    interface onSelectData {
        fun onSelected(modelListLagu: ModelListLagu?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_music, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data: ModelListLagu = items[position]

        Glide.with(context)
                .load(data.strCoverLagu)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_no_image)
                .into(holder.imgCover)

        holder.tvBand.text = data.strNamaBand
        holder.tvTitleMusic.text = data.strJudulMusic
        holder.cvListMusic.setOnClickListener {
            xSelectData.onSelected(data)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvBand: TextView
        var tvTitleMusic: TextView
        var cvListMusic: CardView
        var imgCover: ImageView

        init {
            cvListMusic = itemView.cvListMusic
            tvBand = itemView.tvBand
            tvTitleMusic = itemView.tvTitleMusic
            imgCover = itemView.imgCover
        }
    }

}