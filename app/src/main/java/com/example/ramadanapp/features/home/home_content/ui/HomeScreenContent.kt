package com.example.ramadanapp.features.home.home_content.ui
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ramadanapp.R
import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.common.ui.composable.YouTubeThumbnail
import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse
import com.example.ramadanapp.features.home.home_content.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel(),
    onCategoryClick: (String) -> Unit
) {
    val homeVideosState by viewModel.homeData.collectAsState()

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
                LoadingIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is Resource.Failure -> {
                ErrorScreen(
                    errorMessage = stringResource(R.string.failed_load_videos),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is Resource.Success -> {
                val response = (homeVideosState as Resource.Success<RamadanResponse>).model

                // ✅ Get first section and first category
                val firstSection = response.sections.firstOrNull()
                val topCategory = firstSection?.categories?.firstOrNull()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 110.dp)
                ) {
                    // ✅ Show the first category image at the top
                    topCategory?.let { category ->
                        YouTubeThumbnail(
                            Modifier.height(250.dp),
                            category = category,
                            onClickCategory = {
                                onCategoryClick(category.title) // ✅ Send category ID
                            }
                        )
                    }

                    // ✅ Show sections
                    LazyColumn {
                        items(response.sections) { section ->
                            Column {
                                // ✅ Show section title
                                Text(
                                    text = section.title,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp, horizontal = 16.dp)
                                )

                                // ✅ Show horizontal categories list
                                LazyRow {
                                    items(section.categories) { category ->
                                        YouTubeThumbnail(
                                            modifier = Modifier.height(150.dp).padding(horizontal = 4.dp),
                                            category = category,
                                            onClickCategory = {
                                                Log.d("TAG", "HomeScreenContent: $category")
                                                Log.d("TAG", "HomeScreenContent: ${category.title}")
                                                Log.d("TAG", "HomeScreenContent: ${category.url}")
                                                onCategoryClick(category.title) // ✅ Send category ID
                                            }
                                        )
                                    }
                                }
                            }
                        }
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

