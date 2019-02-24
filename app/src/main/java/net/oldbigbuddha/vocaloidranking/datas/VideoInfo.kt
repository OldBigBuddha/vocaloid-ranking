package net.oldbigbuddha.vocaloidranking.datas

import kotlinx.serialization.Serializable

@Serializable
data class VideoInfo(
    val contentId: String,
    val title: String,
    val viewCounter: Int,
    val thumbnailUrl: String,
    val lengthSeconds: Int,
    val startTime: String
)