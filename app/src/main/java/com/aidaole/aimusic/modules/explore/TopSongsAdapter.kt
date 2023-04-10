package com.aidaole.aimusic.modules.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.TopPlaylistSongItemViewBinding
import com.aidaole.base.datas.entities.PlayListSongs.Songs
import com.aidaole.base.utils.logi

class TopSongsAdapter : RecyclerView.Adapter<SongViewHolder>() {
    companion object {
        private const val TAG = "TopSongsAdapter"
    }

    private var datas: List<Songs> = emptyList()
    var onItemClick: ((item: Songs) -> Unit)? = null

    fun updateDatas(songs: List<Songs>) {
        datas = songs
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_playlist_song_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val item = datas[position]
        holder.layout.number.text = "${position + 1}"
        holder.layout.songName.text = item.name
        holder.layout.author.text = item.al.name
        holder.itemView.setOnClickListener {
            "click: ${item}".logi(TAG)
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

class SongViewHolder(itemView: View) : ViewHolder(itemView) {
    val layout: TopPlaylistSongItemViewBinding

    init {
        layout = TopPlaylistSongItemViewBinding.bind(itemView)
    }
}