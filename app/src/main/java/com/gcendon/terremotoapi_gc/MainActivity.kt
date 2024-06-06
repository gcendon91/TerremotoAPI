package com.gcendon.terremotoapi_gc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: com.gcendon.terremotoapi_gc.Adapter
    private var listQuakes = mutableListOf<Feature>()
    private lateinit var tvTitle: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTitle = findViewById(R.id.tvTitle)
        recyclerView = findViewById(R.id.rvQuakes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = com.gcendon.terremotoapi_gc.Adapter(listQuakes)
        recyclerView.adapter = adapter

        adapter.onItemClickListener = { feature ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("feature", feature)
            startActivity(intent)
        }
        getQuakes30()
    }

    private fun getQuakes30() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIServices::class.java)
                .getQuakes30("significant_month.geojson")
            val response = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    val quakes = response?.features
                    quakes?.forEach { listQuakes.add(it) }
                }
                val metadata = response?.metadata?.title
                tvTitle.text = metadata
                adapter.notifyDataSetChanged()

            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    companion object {
        const val BASE_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/"
    }

}


