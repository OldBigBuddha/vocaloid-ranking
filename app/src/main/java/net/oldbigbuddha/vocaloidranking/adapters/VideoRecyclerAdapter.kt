package net.oldbigbuddha.vocaloidranking.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_video.view.*
import net.oldbigbuddha.vocaloidranking.R
import net.oldbigbuddha.vocaloidranking.datas.VideoInfo
import java.util.*

class VideoRecyclerAdapter(
    private val mVideoInfos: List<VideoInfo>,
    private val mContext: Context
): RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder>() {

    override fun getItemCount(): Int = mVideoInfos.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val videoInfo = mVideoInfos[position]

        holder?.let {
            it.tvTitle.text = videoInfo.title.replace("&amp;", "&")
            it.tvViewCount.text = videoInfo.viewCounter.toString()
            it.tvPublishDate.text = formatDate(videoInfo.startTime)
        }?: IllegalAccessException("ViewHolder is null")
    }

    private fun formatDate(date: String): String = date.replace("-", "/").replace("T", " ").replace("+09:00", "").substring(0, 16)


    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val imageThumbnail: ImageView = view.image_thumbnail
        val tvTitle: TextView       = view.tv_title
        val tvViewCount: TextView   = view.tv_view_count
        val tvPublishDate: TextView = view.tv_publish_date
    }
}