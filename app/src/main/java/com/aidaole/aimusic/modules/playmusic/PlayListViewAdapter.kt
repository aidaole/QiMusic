package com.aidaole.aimusic.modules.playmusic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.aidaole.aimusic.App
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.PlayingItemSimpleViewBinding
import com.aidaole.aimusic.utils.getPaletteColor
import com.aidaole.base.datas.entities.RespSongs
import com.aidaole.base.ext.logi

class PlayListViewAdapter(val datas: MutableList<RespSongs.Song> = mutableListOf()) :
    RecyclerView.Adapter<MusicItemViewHolder>() {
    companion object {
        private const val TAG = "PlayListViewAdapter"
    }

    var seekProgressChangeCallback: ((Int) -> Unit)? = null

    fun updateMusicItems(musicItems: List<RespSongs.Song>) {
        datas.clear()
        datas.addAll(musicItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicItemViewHolder {
        return MusicItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.playing_item_simple_view, null)
        )
    }

    override fun onBindViewHolder(holder: MusicItemViewHolder, position: Int) {
        holder.bind(datas[position], seekProgressChangeCallback)
    }

    override fun getItemCount(): Int = datas.size
}

class MusicItemViewHolder(itemView: View) : ViewHolder(itemView) {
    companion object {
        private const val TAG = "PlayListViewAdapter"
    }

    private val layout = PlayingItemSimpleViewBinding.bind(itemView)

    fun bind(song: RespSongs.Song, seekChange: ((Int) -> Unit)?) {
        val lp = itemView.layoutParams
        if (lp == null) {
            itemView.layoutParams =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        } else {
            itemView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        }
        layout.songName.text = song.name
        layout.songPic.load(song.al.picUrl)
        layout.progressBar.progress = song.progress
        App.get().loadImage(song.al.picUrl) {
            it?.let {
                layout.root.setBackgroundColor(getPaletteColor(it))
            } ?: run {
                "加载图片颜色失败".logi(TAG)
            }
        }
        layout.progressBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                "onStopTrackingTouch-> ${seekBar.progress}".logi(TAG)
                seekChange?.invoke(seekBar.progress)
            }
        })
        layout.root.tag = song.id
    }
}