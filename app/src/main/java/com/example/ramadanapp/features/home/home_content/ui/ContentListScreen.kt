package com.example.ramadanapp.features.home.home_content.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.ramadanapp.common.extentions.extractYouTubeVideoId
import com.example.ramadanapp.common.ui.composable.YouTubePlayer
import com.example.ramadanapp.features.home.home_content.domain.models.Video
import com.example.ramadanapp.features.home.home_content.ui.viewmodel.HomeViewModel

@Composable
fun ContentListScreen(
    category: String,
    selectedVideoId: String,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val homeVideos by viewModel.groupedVideos.collectAsState()
    val videoList = homeVideos[category] ?: emptyList()

    // Track the currently selected video and whether it's playing
    val selectedVideo by remember { mutableStateOf(videoList.find { it.url == selectedVideoId }) }
    var playingVideo by remember { mutableStateOf<Video?>(null) }  // Track which video is playing

    // Filter the list to exclude the selected video
    val filteredVideoList = videoList.filter { it != selectedVideo }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ✅ Show the selected video at the top (only once)
        selectedVideo?.let { video ->
            YouTubePlayer(
                youtubeVideoId = video.url.extractYouTubeVideoId(),
                lifecycleOwner = lifecycleOwner,
                isPlaying = playingVideo == video,  // Only play the selected video
                onThumbnailClick = {
                    // Toggle play/pause when the thumbnail is clicked for the selected video
                    playingVideo = if (playingVideo == video) null else video
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ✅ List of videos in the same category (excluding the selected video)
        LazyColumn {
            item {
                Text(
                    text = "More from $category",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }

            items(filteredVideoList) { video ->
                TopVideoCard(
                    video = video,
                    lifecycleOwner = lifecycleOwner,
                    isPlaying = playingVideo == video,
                    onPlay = {
                        playingVideo = video
                    }
                )
            }
        }
    }
}
