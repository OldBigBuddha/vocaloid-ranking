package net.oldbigbuddha.vocaloidranking

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.kittinunf.result.Result
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObjectResponseResult
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ImplicitReflectionSerializer
import net.oldbigbuddha.vocaloidranking.adapters.VideoRecyclerAdapter
import net.oldbigbuddha.vocaloidranking.datas.ResponseData
import net.oldbigbuddha.vocaloidranking.datas.VideoInfo
import net.oldbigbuddha.vocaloidranking.dialogs.ProgressDialogFragment


class MainActivity : AppCompatActivity() {

    lateinit var progressDialog: ProgressDialogFragment

    @ImplicitReflectionSerializer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_main.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        progressDialog = ProgressDialogFragment()
        progressDialog.show(supportFragmentManager, "progress")

        runBlocking {
            val (_, _, result) = Fuel.get(
                "/search",
                listOf("q" to "VOCALOID殿堂入り")
            ).awaitObjectResponseResult<ResponseData>(kotlinxDeserializerOf())

            when (result) {
                is Result.Failure -> {
                    result.getException().printStackTrace()
                }
                is Result.Success -> {
                    recycler_main.adapter = VideoRecyclerAdapter(
                        result.value.data,
                        this@MainActivity,
                        object : VideoRecyclerAdapter.OnItemClickListener {
                            override fun onItemClick(videoInfo: VideoInfo) {
                                // Reference: https://stackoverflow.com/questions/3004515/sending-an-intent-to-browser-to-open-specific-url
                                startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://www.nicovideo.jp/watch/${videoInfo.contentId}")
                                    )
                                )
                            }
                        })
                    progressDialog.dismiss()

                }

//        Fuel.get("/search", listOf("q" to "VOCALOID殿堂入り")).responseObject<ResponseData> { _, _, result ->
//            when (result) {
//                is Result.Failure -> {
//                    result.getException().printStackTrace()
//                }
//                is Result.Success -> {
//                    recycler_main.adapter = VideoRecyclerAdapter(
//                        result.value.data,
//                        this@MainActivity,
//                        object : VideoRecyclerAdapter.OnItemClickListener {
//                            override fun onItemClick(videoInfo: VideoInfo) {
//                                // Reference: https://stackoverflow.com/questions/3004515/sending-an-intent-to-browser-to-open-specific-url
//                                startActivity(
//                                    Intent(
//                                        Intent.ACTION_VIEW,
//                                        Uri.parse("https://www.nicovideo.jp/watch/${videoInfo.contentId}")
//                                    )
//                                )
//                            }
//                        })
//                    progressDialog.dismiss()
//
//                }
//            }
//        }
            }
        }
    }
}
