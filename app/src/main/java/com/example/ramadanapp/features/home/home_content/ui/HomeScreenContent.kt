package com.example.ramadanapp.features.home.home_content.ui
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.ramadanapp.R
import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.common.extentions.extractYouTubeVideoId
import com.example.ramadanapp.common.ui.composable.YouTubePlayer
import com.example.ramadanapp.common.ui.composable.YouTubeThumbnail
import com.example.ramadanapp.features.home.home_content.domain.models.Video
import com.example.ramadanapp.features.home.home_content.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel(),
    onVideoClick: (Video) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val homeVideosState by viewModel.homeVideos.collectAsState() // ✅ Observe the full Resource state

    Box(modifier = Modifier.fillMaxSize()) {
        CustomTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            onNotificationClick = { /*TODO*/ },
            onSearchClick = { /*TODO*/ }
        )

        when (homeVideosState) {
            is Resource.Progress -> {
                // ✅ Show Loading UI
                LoadingIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is Resource.Failure -> {
                // ✅ Show Error UI
                ErrorScreen(
                    errorMessage =stringResource(R.string.failed_load_videos),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is Resource.Success -> {
                val videos = (homeVideosState as Resource.Success<List<Video>>).model
                val groupedVideos = videos.groupBy { it.category }
                val selectedVideo by remember { mutableStateOf(videos.firstOrNull()) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 110.dp)
                ) {
                    selectedVideo?.let { video ->
                        TopVideoCard(video, lifecycleOwner)
                    }
                    LazyColumn {
                        groupedVideos.forEach { (category, videos) ->
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
    }
}


@Composable
fun VideoCategoryList(videos: List<Video>, onVideoClick: (Video) -> Unit) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(videos) { video ->
            YouTubeThumbnail(modifier = Modifier.padding(horizontal = 8.dp),video,onClickWithVideo = {
                onVideoClick(video)
            })
        }
    }
}

@Composable
fun TopVideoCard(
    video: Video,
    lifecycleOwner: LifecycleOwner,
) {
    YouTubePlayer(
        youtubeVideoId = video.url.extractYouTubeVideoId(),
        lifecycleOwner = lifecycleOwner,
        Modifier.padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
    )
}
@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(errorMessage: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = errorMessage, color = Color.Red, fontSize = 16.sp)
        }
    }
}

