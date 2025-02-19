package com.example.ramadanapp.features.home.home_content.domain.intractor

import app.cash.turbine.test
import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.features.home.home_content.domain.models.Video
import com.example.ramadanapp.features.home.home_content.domain.repository.IHomeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test

class GetHomeVideosUseCaseTest {

    private val homeRepository: IHomeRepository = mockk()
    private val getHomeVideosUseCase = GetHomeVideosUseCase(homeRepository)

    @Test
    fun `invoke should emit loading, then success when repository returns data`() = runTest {
        val mockVideos = listOf(
            Video("Sub1", "Category1", "Title1", "url1"),
            Video("Sub2", "Category2", "Title2", "url2")
        )

        coEvery { homeRepository.getHomeData() } returns mockVideos

        getHomeVideosUseCase().test {
            val firstItem = awaitItem()
            assertTrue(firstItem is Resource.Progress && firstItem.loading) // Ensure it's a loading state

            val secondItem = awaitItem()
            assertTrue(secondItem is Resource.Success) // Ensure it's a success state
            assertEquals(mockVideos, (secondItem as Resource.Success).model) // Validate content

            awaitComplete()
        }
    }

    @Test
    fun `invoke should emit loading, then failure when repository throws exception`() = runTest {
        val exception = RuntimeException("Network Error")
        coEvery { homeRepository.getHomeData() } throws exception

        getHomeVideosUseCase().test {
            val firstItem = awaitItem()
            assertTrue(firstItem is Resource.Progress && firstItem.loading) // Ensure it's a loading state

            val secondItem = awaitItem()
            assertTrue(secondItem is Resource.Failure) // Ensure it's a failure state
            assertEquals(exception, (secondItem as Resource.Failure).exception)

            awaitComplete()
        }
    }
    @Test
    fun `invoke should emit loading, then success with empty list when repository returns no data`() = runTest {
        coEvery { homeRepository.getHomeData() } returns emptyList()

        getHomeVideosUseCase().test {
            val firstItem = awaitItem()
            assertTrue(firstItem is Resource.Progress && firstItem.loading) // Ensure it's a loading state

            val secondItem = awaitItem()
            assertTrue(secondItem is Resource.Success) // Ensure it's a success state
            assertEquals(emptyList<Video>(), (secondItem as Resource.Success).model) // Validate empty list

            awaitComplete()
        }
    }

}
