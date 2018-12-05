package net.oldbigbuddha.vocaloidranking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.core.FuelManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Fuel instance Path", FuelManager.instance.basePath)
        Log.d("Fuel instance Headers", FuelManager.instance.baseHeaders.toString())
        Log.d("Fuel instance Params", FuelManager.instance.baseParams.toString())
    }
}
