package com.example.mobilszoftverlabormovies.ui.list

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.ui.details.MovieDetails
import com.example.mobilszoftverlabormovies.ui.movies.Movies
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

//import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class ListActivity : ComponentActivity() {

    @VisibleForTesting
    internal val moviesViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Config.connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        setContent {
            MoviesMainScreen()
        }
    }
}

@Composable
fun MoviesMainScreen() {
    val navController = rememberNavController()

    ProvideWindowInsets {
        NavHost(navController = navController, startDestination = NavScreen.Home.route) {
            composable(NavScreen.Home.route) {
                Movies(
                    viewModel = hiltViewModel(),
                    selectMenu = {
                        when(it){
                            1 -> navController.navigate(NavScreen.TopRated.route)
                            2 -> navController.navigate(NavScreen.Popular.route)
                            3 -> navController.navigate(NavScreen.NowPlaying.route)
                        }
                    },
                    selectMovie = {
                        navController.navigate("${NavScreen.MovieDetails.route}/$it")
                    }
                )
            }
            composable(NavScreen.TopRated.route) {
                Movies(
                    viewModel = hiltViewModel(),
                    selectMenu = {
                        when(it){
                            0 -> navController.navigate(NavScreen.Home.route)
                            2 -> navController.navigate(NavScreen.Popular.route)
                            3 -> navController.navigate(NavScreen.NowPlaying.route)
                        }
                    },
                    selectMovie = {
                        navController.navigate("${NavScreen.MovieDetails.route}/$it")
                    }
                )
            }
            composable(NavScreen.Popular.route) {
                Movies(
                    viewModel = hiltViewModel(),
                    selectMenu = {
                        when(it){
                            0 -> navController.navigate(NavScreen.Home.route)
                            1 -> navController.navigate(NavScreen.TopRated.route)
                            3 -> navController.navigate(NavScreen.NowPlaying.route)
                        }
                    },
                    selectMovie = {
                        navController.navigate("${NavScreen.MovieDetails.route}/$it")
                    }
                )
            }
            composable(NavScreen.NowPlaying.route) {
                Movies(
                    viewModel = hiltViewModel(),
                    selectMenu = {
                        when(it){
                            0 -> navController.navigate(NavScreen.Home.route)
                            1 -> navController.navigate(NavScreen.TopRated.route)
                            2 -> navController.navigate(NavScreen.Popular.route)
                        }
                    },
                    selectMovie = {
                        navController.navigate("${NavScreen.MovieDetails.route}/$it")
                    }
                )
            }
            composable(
                route = NavScreen.MovieDetails.routeWithArgument,
                arguments = listOf(
                    navArgument(NavScreen.MovieDetails.argument0) { type = NavType.LongType }
                )
            ) { backStackEntry ->
                val movieId =
                    backStackEntry.arguments?.getLong(NavScreen.MovieDetails.argument0)
                        ?: return@composable

                MovieDetails(movieId = movieId, viewModel = hiltViewModel()) {
                    navController.navigateUp()
                }
            }
        }
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")
    object TopRated : NavScreen("Top Rated")
    object Popular : NavScreen("Popular")
    object NowPlaying : NavScreen("Now Playing")

    object MovieDetails : NavScreen("MovieDetails") {

        const val routeWithArgument: String = "MovieDetails/{movieId}"

        const val argument0: String = "movieId"
    }
}
