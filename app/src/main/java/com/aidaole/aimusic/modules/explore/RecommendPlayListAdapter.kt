package com.aidaole.aimusic.modules.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.RecommendPlaylistItemViewBinding
import com.aidaole.base.datas.entities.RespPlayList

class RecommendPlayListAdapter : RecyclerView.Adapter<SongListViewHolder>() {
    private var datas = listOf<RespPlayList.PlaylistsEntity>()
    var onItemClick: ((item: RespPlayList.PlaylistsEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListViewHolder {
        return SongListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recommend_playlist_item_view, null, false)
        )
    }

    override fun onBindViewHolder(holder: SongListViewHolder, position: Int) {
        holder.layout.playlistCover.load(datas[position].coverImgUrl)
        holder.layout.playlistName.text = datas[position].name
        holder.layout.root.setOnClickListener {
            onItemClick?.invoke(datas[position])
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    fun updateDatas(playlists: List<RespPlayList.PlaylistsEntity>) {
        datas = playlists
        notifyDataSetChanged()
    }
}

class SongListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val layout: RecommendPlaylistItemViewBinding

    init {
        layout = RecommendPlaylistItemViewBinding.bind(itemView)
    }
}