package com.santukis.entities.movies

sealed class ImageSize(val value: String)

sealed class BackdropSize(value: String) : ImageSize(value) {
    object W_300 : BackdropSize(value = "w300")
    object W_780 : BackdropSize(value = "w780")
    object W_1280 : BackdropSize(value = "w1280")
    object Original : BackdropSize(value = "original")
}

sealed class PosterSize(value: String) : ImageSize(value) {
    object W_92 : PosterSize(value = "w92")
    object W_154 : PosterSize(value = "w154")
    object W_185 : PosterSize(value = "w185")
    object W_342 : PosterSize(value = "w342")
    object W_500 : PosterSize(value = "w500")
    object W_780 : PosterSize(value = "w780")
    object Original : PosterSize(value = "original")
}

sealed class StillSize(value: String) : ImageSize(value) {
    object W_92 : StillSize(value = "w92")
    object W_185 : StillSize(value = "w185")
    object W_300 : StillSize(value = "w300")
    object Original : StillSize(value = "original")
}

sealed class ProfileSize(value: String) : ImageSize(value) {
    object W_45 : ProfileSize(value = "w45")
    object W_185 : ProfileSize(value = "w185")
    object W_632 : ProfileSize(value = "w632")
    object Original : ProfileSize(value = "original")
}

sealed class LogoSize(value: String) : ImageSize(value) {
    object W_45 : LogoSize(value = "w45")
    object W_92 : LogoSize(value = "w92")
    object W_154 : LogoSize(value = "w154")
    object W_185 : LogoSize(value = "w185")
    object W_300 : LogoSize(value = "w300")
    object W_500 : LogoSize(value = "w500")
    object Original : LogoSize(value = "original")
}