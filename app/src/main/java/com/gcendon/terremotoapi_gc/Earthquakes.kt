package com.gcendon.terremotoapi_gc

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Earthquakes(
    val metadata: Metadata,
    val features: List<Feature>
) : Parcelable

@Parcelize
data class Metadata(
    val title: String
) : Parcelable

@Parcelize
data class Feature(
    val type: String,
    val properties: Properties,
    val geometry: Geometry,
    val id: String
) : Parcelable

@Parcelize
data class Properties(
    val mag: Double,
    val place: String
) : Parcelable

@Parcelize
data class Geometry(
    val coordinates: List<Double>
) : Parcelable