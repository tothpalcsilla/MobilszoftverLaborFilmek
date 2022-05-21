package com.example.mobilszoftverlabormovies.ui.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilszoftverlabormovies.ui.movies.Movies
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
//import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class ListActivity : ComponentActivity() {

    @VisibleForTesting
    private val moviesViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesMainScreen()
        }
    }
}

@Composable
fun MoviesMainScreen() {
    val navController = rememberNavController()

    ProvideWindowInsets {


    }
}
