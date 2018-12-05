package net.oldbigbuddha.vocaloidranking.datas

data class VideoInfo(
    val contentId: String,
    val title: String,
    val viewCounter: Int,
    val thumbnailUrl: String,
    val lengthSeconds: Int,
    val startTime: String
)