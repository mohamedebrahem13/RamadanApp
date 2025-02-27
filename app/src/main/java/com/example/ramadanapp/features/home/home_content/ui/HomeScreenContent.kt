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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.ramadanapp.common.ui.composable.YouTubeThumbnail
import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse
import com.example.ramadanapp.features.home.home_content.ui.viewmodel.home_content.HomeContract
import com.example.ramadanapp.features.home.home_content.ui.viewmodel.home_content.HomeViewModel

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel(),
    onCategoryClick: (String) -> Unit
) {
    val homeState by viewModel.viewState.collectAsState()
    val homeEvent by viewModel.singleEvent.collectAsState(initial = null)

    // Handle events
    LaunchedEffect(homeEvent) {
        homeEvent?.let { event ->
            when (event) {
                is HomeContract.HomeEvent.NavigateToCategory -> {
                    onCategoryClick(event.categoryTitle)
                }
                is HomeContract.HomeEvent.ShowError -> {
                    Log.e("HomeScreen", "Error: ${event.exception.message}")
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CustomTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            onNotificationClick = { /*TODO*/ },
            onSearchClick = { /*TODO*/ }
        )

        when {
            homeState.isLoading -> {
                LoadingIndicator(modifier = Modifier.align(Alignment.Center))
            }
            homeState.exception != null -> {
                ErrorScreen(
                    errorMessage = homeState.exception?.message ?: stringResource(R.string.failed_load_videos),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
           homeState.homeData != null  -> {
                HomeContent(homeState.homeData!!,onAction = { action -> viewModel.onActionTrigger(action) }
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    response: RamadanResponse,
    onAction: (HomeContract.HomeAction) -> Unit
) {
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
                onClickCategory = { onAction(HomeContract.HomeAction.SelectCategory(category.title)) } // ✅ Send action
            )
        }

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
                                modifier = Modifier
                                    .height(150.dp)
                                    .padding(horizontal = 4.dp),
                                category = category,
                                onClickCategory = { onAction(HomeContract.HomeAction.SelectCategory(category.title)) } // ✅ Send action
                            )
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

