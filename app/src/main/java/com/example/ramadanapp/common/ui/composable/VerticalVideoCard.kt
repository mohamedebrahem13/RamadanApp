package com.example.ramadanapp.common.ui.composable

import coil.compose.AsyncImage
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ramadanapp.common.extentions.extractYouTubeVideoId
import com.example.ramadanapp.common.extentions.getYouTubeThumbnailUrl

@Composable
fun VerticaVideoCard(
    videoUrl: String,
    onClick: (video: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val videoId = videoUrl.extractYouTubeVideoId()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp) // Set height of the card
            .padding(8.dp), // Add padding around the card
        shape = RoundedCornerShape(16.dp), // Rounded corners
    ) {
        Box(modifier = Modifier.fillMaxSize().clickable { onClick(videoUrl) }) {
            AsyncImage(
                model = getYouTubeThumbnailUrl(videoId),
                contentDescription = "Video Thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Video",
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                        .padding(8.dp),
                    tint = Color.White
                )
            }
        }
    }
}
