package net.oldbigbuddha.vocaloidranking

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse
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

//        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()

        progressDialog = ProgressDialogFragment()
        progressDialog.show(supportFragmentManager, "progress")

        Fuel.get("/search", listOf("q" to "VOCALOID殿堂入り")).responseString { request, response, result ->
            val res = Json.unquoted.parse<ResponseData>( result.get() )
                res?.let {
                    recycler_main.adapter = VideoRecyclerAdapter(
                        it.data,
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
                } ?: throw IllegalAccessException("Response data mustn't be null")

        }
        }
//        Fuel.get("/search", listOf("q" to "VOCALOID殿堂入り")).responseObject<ResponseData> { _, _, result ->
//            //do something with response
//            when (result) {
////                is Result.Failure -> {
////                    Log.d("Request", request.toString())
////                    val ex = result.getException()
////                    ex.printStackTrace()
////                }
////                is Result.Success -> {
//                    val data = result.get()
////                    val res = moshi.adapter(ResponseData::class.java).fromJson(data)
////                    val res = Json.unquoted.parse<ResponseData>(data)
//                res?.let {
//                    recycler_main.adapter = VideoRecyclerAdapter(
//                        it.data,
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
//                } ?: throw IllegalAccessException("Response data mustn't be null")
////                }
////            }
//            }
//        }
    }
