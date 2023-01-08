package com.santukis.entities.movies

sealed class Image<Size : ImageSize>(private val path: String) {
    private val baseUrl = "https://image.tmdb.org/t/p/"

    fun getUrl(size: Size): String = "${baseUrl}${size.value}${path}"
}

class BackdropImage(path: String) : Image<BackdropSize>(path)

class PosterImage(path: String) : Image<PosterSize>(path)

class StillImage(path: String) : Image<StillSize>(path)

class ProfileImage(path: String) : Image<ProfileSize>(path)

class LogoImage(path: String) : Image<LogoSize>(path)
