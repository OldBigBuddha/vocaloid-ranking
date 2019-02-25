package net.oldbigbuddha.vocaloidranking.datas

import kotlinx.serialization.Serializable

@Serializable
data class ResponseData(
    val meta: Meta,
    val data: List<VideoInfo>
)