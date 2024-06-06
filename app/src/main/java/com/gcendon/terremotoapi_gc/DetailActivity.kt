package com.gcendon.terremotoapi_gc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvLatitud: TextView
    private lateinit var tvLongitud: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val feature = intent.getParcelableExtra<Feature>("feature")

        tvTitle = findViewById(R.id.tvDescription)
        tvLatitud = findViewById(R.id.tvLatitud)
        tvLongitud = findViewById(R.id.tvLongitud)

        tvTitle.text = feature?.properties?.place
        tvLatitud.text = "latitud: " + feature!!.geometry.coordinates[0].toString()
        tvLongitud.text = "longitud: " + feature!!.geometry.coordinates[1].toString()
    }
}