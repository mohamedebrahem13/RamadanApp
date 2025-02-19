package com.example.ramadanapp.common.extentions

fun String.extractYouTubeVideoId(): String {
    val regex = "(?:https?://)?(?:www\\.)?(?:youtube\\.com/(?:[^/]+/.+/|(?:v|e(?:mbed)?)|.*[?&]v=)|youtu\\.be/)([^\"&?/ ]{11})".toRegex()
    return regex.find(this)?.groups?.get(1)?.value ?: ""
}
fun getYouTubeThumbnailUrl(videoId: String): String {
    return "https://img.youtube.com/vi/$videoId/0.jpg"
}