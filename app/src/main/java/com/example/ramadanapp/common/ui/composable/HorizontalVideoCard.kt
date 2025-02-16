package com.example.ramadanapp.common.ui.composable

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import com.example.ramadanapp.common.extentions.getYouTubeThumbnailUrl
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner,
    isPlaying: Boolean,
    onThumbnailClick: () -> Unit
) {
    if (isPlaying) {
        // Show YouTube player
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Set a fixed height for video player
                .padding(8.dp)
                .clip(RoundedCornerShape(10.dp)),
            factory = { it: Context ->
                YouTubePlayerView(context = it).apply {
                    lifecycleOwner.lifecycle.addObserver(this)

                    addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(youtubeVideoId, startSeconds = 0f)
                        }
                    })
                }
            }
        )
    } else {
        // Show the thumbnail with play button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Set a fixed height
                .padding(8.dp)
                .clickable { onThumbnailClick() }
                .clip(RoundedCornerShape(10.dp))
        ) {
            AsyncImage(
                model = getYouTubeThumbnailUrl(youtubeVideoId),
                contentDescription = "Video Thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play Video",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(50.dp)
                    .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                    .padding(8.dp),
                tint = Color.White
            )
        }
    }
}


