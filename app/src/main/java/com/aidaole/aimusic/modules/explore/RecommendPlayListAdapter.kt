package com.aidaole.aimusic.modules.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.SongListItemBinding
import com.aidaole.base.datas.entities.RespPlayList

class RecommendPlayListAdapter : RecyclerView.Adapter<SongListViewHolder>() {
    private var datas = listOf<RespPlayList.PlaylistsEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListViewHolder {
        return SongListViewHolder.inflate(LayoutInflater.from(parent.context))
    }

    override fun onBindViewHolder(holder: SongListViewHolder, position: Int) {
        holder.layout.playlistCover.load(datas[position].coverImgUrl)
        holder.layout.playlistName.text = datas[position].name
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
    val layout: SongListItemBinding

    companion object {
        fun inflate(inflater: LayoutInflater) =
            SongListViewHolder(inflater.inflate(R.layout.song_list_item, null, false))
    }

    init {
        layout = SongListItemBinding.bind(itemView)
    }
}