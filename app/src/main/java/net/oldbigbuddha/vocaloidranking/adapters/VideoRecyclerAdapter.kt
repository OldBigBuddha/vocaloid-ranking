package net.oldbigbuddha.vocaloidranking.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pagenation.view.*
import kotlinx.android.synthetic.main.item_video.view.*
import net.oldbigbuddha.vocaloidranking.R
import net.oldbigbuddha.vocaloidranking.datas.VideoInfo
import java.util.*

class VideoRecyclerAdapter(
    private val mVideoInfos: List<VideoInfo>,
    private val mContext: Context,
    private val mListener: OnItemClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_ITEM = 0
        private const val TYPE_FOOTER = 1
    }

    interface OnItemClickListener {
        fun onItemClick(videoInfo: VideoInfo)
    }

    override fun getItemCount(): Int = mVideoInfos.size + 1

    override fun getItemViewType(position: Int): Int = when (position) {
        mVideoInfos.size -> TYPE_FOOTER
        else -> TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? = when (viewType) {
            TYPE_FOOTER -> FooterItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_pagenation, parent, false))
            else -> ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder?.let { it ->

            when(it) {
                is ItemViewHolder -> {
                    val videoInfo = mVideoInfos[position]

                    it.bindListener(videoInfo, mListener)
                    it.tvTitle.text = videoInfo.title.replace("&amp;", "&")
                    it.tvViewCount.text = videoInfo.viewCounter.toString()
                    it.tvPublishDate.text = formatDate(videoInfo.startTime)

                    Picasso.get()
                        .load(videoInfo.thumbnailUrl)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.error)
                        .fit()
                        .into(it.imageThumbnail)

                }

                is FooterItemHolder -> {
                    it.btNext.setOnClickListener {
                        Toast.makeText(mContext, "Clicked NEXT", Toast.LENGTH_SHORT).show()
                    }
                    it.btPrev.setOnClickListener {
                        Toast.makeText(mContext, "Clicked PREV", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }?: IllegalAccessException("ViewHolder is null")
    }

    private fun formatDate(date: String): String = date.replace("-", "/").replace("T", " ").replace("+09:00", "").substring(0, 16)


    class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val imageThumbnail: ImageView = view.image_thumbnail
        val tvTitle: TextView       = view.tv_title
        val tvViewCount: TextView   = view.tv_view_count
        val tvPublishDate: TextView = view.tv_publish_date

        fun bindListener(item: VideoInfo, listener: OnItemClickListener) {
            itemView?.let { it ->
                it.setOnClickListener { listener.onItemClick(item) }
            }
        }
    }

    class FooterItemHolder(val view: View): RecyclerView.ViewHolder(view) {
        val btNext: Button = view.bt_next
        val btPrev: Button = view.bt_prev
    }
}