package com.example.ramadanapp.common.ui.composable

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner,
    modifier: Modifier = Modifier
) {
    var youTubePlayerInstance: YouTubePlayer? by remember { mutableStateOf(null) }
    var playerView: YouTubePlayerView? by remember { mutableStateOf(null) }

    // Show YouTube player
    AndroidView(
        modifier = modifier,
        factory = { context: Context ->
            YouTubePlayerView(context).apply {
                playerView = this
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayerInstance = youTubePlayer
                        youTubePlayer.cueVideo(youtubeVideoId, startSeconds = 0f) // ✅ Load video without restarting player
                    }
                })
            }
        }
    )

    // When video ID changes, load new video
    DisposableEffect(youtubeVideoId) {
        youTubePlayerInstance?.cueVideo(youtubeVideoId, startSeconds = 0f) // ✅ Change video smoothly

        onDispose {
            playerView?.release() // ✅ Manually release the player
            playerView = null
            youTubePlayerInstance = null
        }

    }
}