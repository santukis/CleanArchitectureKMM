package com.santukis.entities.movies

import com.santukis.entities.configuration.Region

class Video(
    val id: String,
    val region: Region,
    val name: String,
    val type: String,
    val size: Int,
    val official: Boolean,
    val url: String
)
