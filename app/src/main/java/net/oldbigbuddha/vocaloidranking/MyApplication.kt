package net.oldbigbuddha.vocaloidranking

import android.app.Application
import com.github.kittinunf.fuel.core.FuelManager

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FuelManager.instance.basePath = "https://api.search.nicovideo.jp/api/v2/video/contents/search"
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "VOCALOID Ranking")
        FuelManager.instance.baseParams = listOf(
            "targets" to "tags",
            "fields" to "contentId,title,viewCounter,startTime,lengthSeconds,thumbnailUrl",
            "_sort" to "-viewCounter",
            "_limit" to "20",
            "_context" to "VOCALOID Ranking"
        )
    }
}