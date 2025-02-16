package com.example.ramadanapp.features.home.homescreen.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.ramadanapp.common.extentions.extractYouTubeVideoId
import com.example.ramadanapp.common.ui.composable.VerticaVideoCard
import com.example.ramadanapp.common.ui.composable.YouTubePlayer
import com.example.ramadanapp.features.home.homescreen.domain.models.Video
import com.example.ramadanapp.features.home.homescreen.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner,
    onVideoClick: (Video) -> Unit
) {
    val homeVideos by viewModel.groupedVideos.collectAsState()
    var isPlaying by remember { mutableStateOf(false) }
    val selectedVideo by remember { mutableStateOf(homeVideos.values.firstOrNull()?.firstOrNull()) }

    Box(modifier = Modifier.fillMaxSize()) {
        // ✅ TopBar
        CustomTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            onNotificationClick = { /*TODO*/ },
            onSearchClick = { /*TODO*/ }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 110.dp) // Adjust height to fit under TopBar
        ) {
            selectedVideo?.let { video ->
                TopVideoCard(video, lifecycleOwner, isPlaying) { isPlaying = true }
            }
            LazyColumn {
                homeVideos.forEach { (category, videos) ->
                    item {
                        Text(
                            text = category,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp, horizontal = 16.dp)
                        )
                    }

                    item {
                        VideoCategoryList(videos, onVideoClick)
                    }
                }
            }
        }
    }
}


@Composable
fun VideoCategoryList(videos: List<Video>, onVideoClick: (Video) -> Unit) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(videos) { video ->
            VerticaVideoCard(video.url,   onClick = { vid ->
                println("Video clicked: $vid")
            })
        }
    }
}

@Composable
fun TopVideoCard(
    video: Video,
    lifecycleOwner: LifecycleOwner,
    isPlaying: Boolean,
    onPlay: () -> Unit
) {
    YouTubePlayer(
        youtubeVideoId = video.url.extractYouTubeVideoId(),
        lifecycleOwner = lifecycleOwner,
        isPlaying = isPlaying,
        onThumbnailClick = onPlay
    )
}


