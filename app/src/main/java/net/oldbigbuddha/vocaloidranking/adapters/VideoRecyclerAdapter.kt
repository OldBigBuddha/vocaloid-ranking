package net.oldbigbuddha.vocaloidranking.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_video.view.*
import net.oldbigbuddha.vocaloidranking.R
import net.oldbigbuddha.vocaloidranking.datas.VideoInfo

class VideoRecyclerAdapter(
    private val mVideoInfos: List<VideoInfo>,
    private val mContext: Context,
    private val mListener: OnItemClickListener
): RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder>() {
    
    interface OnItemClickListener {
        fun onItemClick(videoInfo: VideoInfo)
    }

    override fun getItemCount(): Int = mVideoInfos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoInfo = mVideoInfos[position]

        holder.bindListener(videoInfo, mListener)
        holder.tvTitle.text = videoInfo.title.replace("&amp;", "&")
        holder.tvViewCount.text = videoInfo.viewCounter.toString()
        holder.tvPublishDate.text = formatDate(videoInfo.startTime)

//        Picasso.get()
//            .load(videoInfo.thumbnailUrl)
//            .placeholder(R.drawable.placeholder)
//            .error(R.drawable.error)
//            .fit()
//            .into(holder.imageThumbnail)
    }

    private fun formatDate(date: String): String = date.replace("-", "/").replace("T", " ").replace("+09:00", "").substring(0, 16)


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageThumbnail: ImageView = view.image_thumbnail
        val tvTitle: TextView       = view.tv_title
        val tvViewCount: TextView   = view.tv_view_count
        val tvPublishDate: TextView = view.tv_publish_date

        fun bindListener(item: VideoInfo, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(item) } }
    }
}