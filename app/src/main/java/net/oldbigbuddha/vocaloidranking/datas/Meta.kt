package net.oldbigbuddha.vocaloidranking.datas

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val status: Int,
    val totalCount: Int,
    val id: String
)