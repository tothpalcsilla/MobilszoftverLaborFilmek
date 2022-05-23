package com.example.mobilszoftverlabormovies

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mobilszoftverlabormovies.ui.details.DetailViewModel
import com.example.mobilszoftverlabormovies.ui.details.MovieDetails
import com.example.mobilszoftverlabormovies.ui.list.ListActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityMovieDetailsTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ListActivity>()

    private lateinit var activity: ListActivity

    @Before
    fun init() {
        composeTestRule.activityRule.scenario.onActivity {
            activity = it
        }
    }

    @Test
    fun posterDetailsCaptainAmericaTheFirstAvengerLoadingTest() {
        composeTestRule.setContent {
            val viewModel = hiltViewModel<DetailViewModel>()
            viewModel.loadMovieById(0)

            MovieDetails(
                movieId = 0,
                viewModel = viewModel
            )
        }

        composeTestRule
            .onNodeWithText("Captain America: The First Avenger", ignoreCase = true)
            .assertIsDisplayed()
    }

    @Test
    fun posterDetailsTheLionKingLoadingTest() {
        composeTestRule.setContent {
            val viewModel = hiltViewModel<DetailViewModel>()
            viewModel.loadMovieById(8587)

            MovieDetails(
                movieId = 8587,
                viewModel = viewModel
            )
        }

        composeTestRule
            .onNodeWithText("Az oroszlánkirály", ignoreCase = true)
            .assertIsDisplayed()
    }

    @Test
    fun posterDetailsLionKingIIILoadingTest() {
        composeTestRule.setContent {
            val viewModel = hiltViewModel<DetailViewModel>()
            MovieDetails(
                movieId = 11430,
                viewModel = viewModel
            )
        }

        composeTestRule
            .onNodeWithText("Az oroszlánkirály 3. - Hakuna Matata", ignoreCase = true)
            .assertIsDisplayed()
    }
}