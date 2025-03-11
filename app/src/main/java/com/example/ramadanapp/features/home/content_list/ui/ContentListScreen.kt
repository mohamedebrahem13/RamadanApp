package com.example.ramadanapp.features.home.content_list.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.ramadanapp.R
import com.example.ramadanapp.common.ui.composable.YouTubePlayer
import com.example.ramadanapp.common.ui.composable.YouTubeThumbnail
import com.example.ramadanapp.features.home.home_content.ui.ErrorScreen
import com.example.ramadanapp.features.home.home_content.ui.LoadingIndicator

@Composable
fun ContentListScreen(
    playListId: String,
    viewModel: ContentListViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val contentListEvent by viewModel.singleEvent.collectAsState(initial = null)

    LaunchedEffect(playListId) {
        viewModel.onActionTrigger(ContentListContract.ContentListAction.GetItems(playListId))
    }

    LaunchedEffect(contentListEvent) {
        contentListEvent?.let {
            when (it) {
                is ContentListContract.ContentListEvent.ShowError -> {
                    Log.e("ContentListScreen", "Error: ${it.exception.message}")
                }
            }
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val viewState by viewModel.viewState.collectAsState()

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.Green)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = Color.White
                )
            }
        }

        when {
            viewState.isLoading -> {
                LoadingIndicator()
            }

            viewState.exception != null -> {
                ErrorScreen(errorMessage = viewState.exception!!.message ?: "An error occurred")
            }

            else -> {
                viewState.playingVideo?.let { video ->
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(Color.Black)
                    ) {
                        YouTubePlayer(
                            youtubeVideoId = video.videoId,
                            lifecycleOwner = lifecycleOwner,
                            modifier = Modifier.wrapContentSize()
                        )
                    }
                }

                viewState.playingVideo?.let { video ->
                        Row(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = video.title,
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.weight(1f),
                                softWrap = false,
                                overflow = TextOverflow.Ellipsis
                            )
                            IconButton(onClick = { /* Handle Share */ }) {
                                Icon(imageVector = Icons.Default.Share, contentDescription = stringResource(R.string.share))
                            }
                            IconButton(onClick = { /* Handle Save */ }) {
                                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = stringResource(R.string.save))
                            }
                        }
                        Text(
                            text = stringResource(R.string.more_videos),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                textDecoration = TextDecoration.Underline
                            ), modifier = Modifier.padding(horizontal = 16.dp)
                        )

                }

                LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                    items(viewState.filteredVideos) { videoItem ->
                        YouTubeThumbnail(
                            modifier = Modifier.padding(vertical = 8.dp),
                            item = videoItem,
                            onClickWithItem = { viewModel.setPlayingVideo(videoItem) }
                        )
                    }
                }
            }
        }
    }
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


