package com.example.ramadanapp.features.home.home_content.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.ramadanapp.R
import com.example.ramadanapp.common.extentions.extractYouTubeVideoId
import com.example.ramadanapp.common.ui.composable.YouTubePlayer
import com.example.ramadanapp.common.ui.composable.YouTubeThumbnail
import com.example.ramadanapp.features.home.home_content.ui.viewmodel.ContentListViewModel


@Composable
fun ContentListScreen(
    category: String,
    viewModel: ContentListViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    Log.d("TAG", "ContentListScreen: $category")

    // ✅ Trigger filtering when the screen starts
    LaunchedEffect(Unit) {
        viewModel.setCategoryFilter(category)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val videoList by viewModel.filteredVideos.collectAsState()

    var playingVideo by remember { mutableStateOf(videoList.firstOrNull()) }

    // ✅ Automatically set the first video when the list updates
    LaunchedEffect(videoList) {
        if (playingVideo == null && videoList.isNotEmpty()) {
            playingVideo = videoList.first()
        }
    }

    val filteredVideoList = videoList.filter { it != playingVideo }

    Column(modifier = Modifier.fillMaxSize()) {
        // ✅ Top Bar with Back Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.Green)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = Color.White
                )
            }
        }

        // ✅ Video Player Below Top Bar
        playingVideo?.let { video ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
            ) {
                YouTubePlayer(
                    youtubeVideoId = video.url.extractYouTubeVideoId(),
                    lifecycleOwner = lifecycleOwner,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        playingVideo?.let { video ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = video.title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
                IconButton(onClick = { /* Handle Share */ }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = stringResource(R.string.share))
                }
                IconButton(onClick = { /* Handle Save */ }) {
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = stringResource(R.string.save))
                }
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(stringResource(R.string.more_videos))
                    }
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append(video.category)
                    }
                },
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(filteredVideoList) { item ->
                YouTubeThumbnail(
                    modifier = Modifier.padding(vertical = 8.dp),
                    item = item,
                    onClickWithItem = { playingVideo = item }
                )
            }
        }
    }
}
