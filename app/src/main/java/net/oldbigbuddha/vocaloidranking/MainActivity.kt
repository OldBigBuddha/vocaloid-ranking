package net.oldbigbuddha.vocaloidranking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import net.oldbigbuddha.vocaloidranking.adapters.VideoRecyclerAdapter
import net.oldbigbuddha.vocaloidranking.datas.ResponseData

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_main.layoutManager = LinearLayoutManager(this)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Fuel.get("/search", listOf("q" to "VOCALOID殿堂入り")).responseString { request, response, result ->
            //do something with response
            when (result) {
                is Result.Failure -> {
                    Log.d("Request", request.toString())
                    val ex = result.getException()
                    ex.printStackTrace()
                }
                is Result.Success -> {
                    val data = result.get()
                    val res = moshi.adapter(ResponseData::class.java).fromJson(data)
                    res?.let {
                        recycler_main.adapter = VideoRecyclerAdapter(it.data, this@MainActivity)
                    } ?: throw IllegalAccessException("Response data mustn't be null")
                }
            }
        }    }
}
