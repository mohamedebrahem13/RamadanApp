package com.example.ramadanapp.features.home.home_content.ui.content_list

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


@Composable
fun ContentListScreen(
    category: String,
    viewModel: ContentListViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    //  Send action when the screen opens
    LaunchedEffect(category) {
        Log.d("TAG", "ContentListScreen: $category")
        viewModel.onActionTrigger(ContentListContract.ContentListAction.GetItems(category))
    }
    val contentListEvent by viewModel.singleEvent.collectAsState(initial = null)

    //  Handle events
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

    Column(modifier = Modifier.fillMaxSize()) {
        //  Top Bar with Back Button
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
        viewState. playingVideo?.let { video ->
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

        viewState.playingVideo?.let { video ->
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
            items(viewState.filteredVideos) { item ->
                YouTubeThumbnail(
                    modifier = Modifier.padding(vertical = 8.dp),
                    item = item,
                    onClickWithItem = { viewModel.setPlayingVideo(item) }
                )
            }
        }
    }
}