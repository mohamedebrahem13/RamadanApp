package com.example.ramadanapp.features.home.home_content.domain.intractor

import app.cash.turbine.test
import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.common.data.models.RamadanAppException
import com.example.ramadanapp.features.home.home_content.domain.models.Category
import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse
import com.example.ramadanapp.features.home.home_content.domain.models.Section
import com.example.ramadanapp.features.home.home_content.domain.repository.IHomeRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class GetHomeDataFromRemoteUseCaseTest {

    private val homeRepository: IHomeRepository = mockk()
    private val getHomeVideosUseCase = GetHomeDataFromRemoteUseCase(homeRepository)

    @Test
    fun `invoke should emit loading, then success when repository returns data`() = runTest {
        val mockCategories = listOf(
            Category("PL123", "Islamic Lessons", "https://example.com/lesson1"),
            Category("PL456", "Ramadan Talks", "https://example.com/talk1")
        )

        val mockSections = listOf(
            Section("Section 1", mockCategories),
            Section("Section 2", mockCategories)
        )

        val ramadanResponse = RamadanResponse(
            sections = mockSections,
            totalVideoCount = 2,
            playlistCount = 2
        )

        // Mock repository responses
        coEvery { homeRepository.getHomeDataFromRemote() } returns ramadanResponse
        coEvery { homeRepository.saveRamadanResponse(any()) } just Runs // Mock saveRamadanResponse

        getHomeVideosUseCase().test {
            // First emission should be loading
            assertTrue(awaitItem() is Resource.Progress)

            // Second emission should be success
            val successItem = awaitItem()
            assertTrue(successItem is Resource.Success)
            assertEquals(ramadanResponse, (successItem as Resource.Success).model)

            // Ensure saveRamadanResponse was called with the correct response
            coVerify(exactly = 1) { homeRepository.saveRamadanResponse(ramadanResponse) }

            // Handle possible final Progress(false)
            val thirdItem = expectMostRecentItem()
            if (thirdItem is Resource.Progress) {
                assertFalse(thirdItem.loading) // Ensure loading is false at the end
            }
            awaitComplete() // Ensure the flow completes
        }
    }

    @Test
    fun `invoke should emit loading, then failure when repository throws exception`() = runTest {
        val exception = RuntimeException("Network Error")
        coEvery { homeRepository.getHomeDataFromRemote() } throws exception
        coEvery { homeRepository.saveRamadanResponse(any()) } just Runs

        getHomeVideosUseCase().test {
            val firstItem = awaitItem()
            assertTrue(firstItem is Resource.Progress && firstItem.loading) // Ensure it's a loading state

            val secondItem = awaitItem()
            assertTrue(secondItem is Resource.Failure) // Ensure it's a failure state

            // ✅ Check if exception is wrapped in RamadanAppException.Unknown
            val actualException = (secondItem as Resource.Failure).exception
            assertTrue(actualException is RamadanAppException.Unknown) // Ensure correct exception type
            assertEquals("Unknown error appeared", actualException.message) // Match the expected error message

            // Handle possible final Progress(false)
            val thirdItem = expectMostRecentItem()
            if (thirdItem is Resource.Progress) {
                assertFalse(thirdItem.loading) // Ensure loading is false at the end
            }

            awaitComplete()
        }
    }

    @Test
    fun `invoke should emit loading, then success with empty list when repository returns no data`() = runTest {
        val emptyResponse = RamadanResponse(emptyList(), 0, 0) // Ensure correct parameters

        coEvery { homeRepository.getHomeDataFromRemote() } returns emptyResponse
        coEvery { homeRepository.saveRamadanResponse(any()) } just Runs // Mock saveRamadanResponse

        getHomeVideosUseCase().test {
            val firstItem = awaitItem()
            assertTrue(firstItem is Resource.Progress && firstItem.loading) // Ensure it's a loading state

            val secondItem = awaitItem()
            assertTrue(secondItem is Resource.Success) // Ensure it's a success state
            assertEquals(emptyResponse, (secondItem as Resource.Success).model) // Validate empty response

            // Handle possible final Progress(false)
            val thirdItem = expectMostRecentItem()
            if (thirdItem is Resource.Progress) {
                assertFalse(thirdItem.loading) // Ensure loading is false at the end
            }
            awaitComplete()
        }
    }

}