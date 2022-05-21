package com.example.mobilszoftverlabormovies.ui.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mobilszoftverlabormovies.model.Movie
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun HomeMovies(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    selectMovie: (Long) -> Unit,
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.background)
    ) {
        movies.forEach { movie ->
            HomeMovie(
                movie = movie,
                selectMovie = selectMovie
            )
        }
    }
}

@Composable
private fun HomeMovie(
    modifier: Modifier = Modifier,
    movie: Movie,
    selectMovie: (Long) -> Unit = {},
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clickable(
                onClick = { selectMovie(movie.id) }
            ),
        color = MaterialTheme.colors.onBackground,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout {
            val (image, title, content) = createRefs()
            /*NetworkImage(
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    },
                url = movie.,
            )*/

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    }
                    .padding(8.dp),
                text = movie.title,
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
            )

            Text(
                text = movie.release_date,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}
