package com.example.mobilszoftverlabormovies.ui.movies

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.ui.list.ListViewModel
import com.example.mobilszoftverlabormovies.R

@Composable
fun Movies(
    viewModel: ListViewModel,
    selectMovie: (Long) -> Unit
) {
    val movies: List<Movie> by viewModel.movieList.collectAsState(initial = listOf())
    val isLoading: Boolean by viewModel.isLoading

    ConstraintLayout {
        val (body, progress) = createRefs()
        Scaffold(
            backgroundColor = MaterialTheme.colors.primarySurface,
            topBar = { MovieAppBar() },
            modifier = Modifier.constrainAs(body) {
                top.linkTo(parent.top)
            },
        ){ innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            HomeMovies(modifier, movies, selectMovie)
        }
        CircularProgressIndicator(
            modifier = Modifier
                .constrainAs(progress) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Preview
@Composable
private fun MovieAppBar() {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = Color.Green,
        modifier = Modifier.height(58.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            text = stringResource(R.string.app_name),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
