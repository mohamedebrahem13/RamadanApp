package com.example.ramadanapp.common.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import coil.compose.AsyncImage
import com.example.ramadanapp.common.extentions.extractYouTubeVideoId
import com.example.ramadanapp.common.extentions.getYouTubeThumbnailUrl
import com.example.ramadanapp.features.home.home_content.domain.models.Category
import com.example.ramadanapp.features.home.home_content.domain.models.Item

@Composable
fun YouTubeThumbnail(
    modifier: Modifier = Modifier,
    category: Category? = null,  // Nullable category
    item: Item? = null,  // Nullable item
    onClickCategory: ((Category) -> Unit)? = null,
    onClickWithItem: ((Item) -> Unit)? = null,  // Updated function name
    isVertical: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                category?.let { onClickCategory?.invoke(it) } // Invoke category click if available
                    ?: item?.let { onClickWithItem?.invoke(it) } // Otherwise, invoke item click
            }
            .clip(RoundedCornerShape(10.dp))
    ) {
        val imageUrl = item?.url?.extractYouTubeVideoId()?.let { getYouTubeThumbnailUrl(it) } ?: category?.url

        AsyncImage(
            model = imageUrl,
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