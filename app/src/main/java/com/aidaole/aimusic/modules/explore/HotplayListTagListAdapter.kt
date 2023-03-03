package com.aidaole.aimusic.modules.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.HotPlaylistItemViewBinding
import com.aidaole.base.datas.entities.HotPlayListTags.Tags

class HotplayListTagListAdapter : RecyclerView.Adapter<HotplayListTagViewHolder>() {

    private var datas = listOf<Tags>()

    fun updateDatas(newDatas: List<Tags>) {
        datas = newDatas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotplayListTagViewHolder {
        return HotplayListTagViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.hot_playlist_item_view, null, false)
        )
    }

    override fun onBindViewHolder(holder: HotplayListTagViewHolder, position: Int) {
        holder.layout.tagName.text = datas[position].name
    }

    override fun getItemCount(): Int {
        return datas.size
    }

}

class HotplayListTagViewHolder(itemView: View) : ViewHolder(itemView) {
    val layout: HotPlaylistItemViewBinding

    init {
        layout = HotPlaylistItemViewBinding.bind(itemView)
    }
}