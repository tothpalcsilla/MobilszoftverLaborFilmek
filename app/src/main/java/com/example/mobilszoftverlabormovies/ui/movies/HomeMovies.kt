package com.example.mobilszoftverlabormovies.ui.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.R
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
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(
                onClick = { selectMovie(movie.id) }
            ),
        color = colorResource(R.color.bluegray_800),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
    ) {
        ConstraintLayout {
            val (image, title, rateRow, overview) = createRefs()

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Config.base_url + movie.poster_path)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        centerVerticallyTo(parent)
                    }
                    .height(130.dp)
                    .padding(8.dp)
                    .aspectRatio(1.0f)
                    .clip(CircleShape)
            )

            Text(
                text = movie.title,
                style = MaterialTheme.typography.h6.copy(color = colorResource(R.color.white)),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(image.end)
                    }
                    .width(260.dp)
                    .padding(top = 12.dp)
            )

            Row(
                modifier = Modifier
                    .constrainAs(rateRow) {
                        top.linkTo(title.bottom)
                        start.linkTo(image.end)
                    }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    tint = colorResource(R.color.red),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )

                Text(
                    text = movie.popularity.toString(),
                    style = MaterialTheme.typography.body2.copy(color = colorResource(R.color.white)),
                    modifier = Modifier.padding(end = 16.dp, top = 4.dp)
                )

                Icon(
                    imageVector = Icons.Filled.StarRate,
                    tint = colorResource(R.color.yellow),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )

                Text(
                    text = movie.vote_average.toString(),
                    style = MaterialTheme.typography.body2.copy(color = colorResource(R.color.white)),
                    modifier = Modifier.padding(end = 32.dp, top = 4.dp)
                )
            }

            Text(
                text = movie.overview,
                style = MaterialTheme.typography.body2.copy(color = colorResource(R.color.white)),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .constrainAs(overview) {
                        top.linkTo(rateRow.bottom)
                        start.linkTo(image.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .width(260.dp)
                    .padding(bottom = 12.dp)
            )
        }
    }
}
