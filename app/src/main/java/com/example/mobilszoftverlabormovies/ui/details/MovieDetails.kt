package com.example.mobilszoftverlabormovies.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.R

@Composable
fun MovieDetails(
    movieId: Long,
    viewModel: DetailViewModel,
    pressOnBack: () -> Unit = {}
) {
    LaunchedEffect(key1 = movieId) {
        viewModel.loadMovieById(movieId)
    }

    val details: Movie? by viewModel.movieDetailsFlow.collectAsState(initial = null)
    details?.let { movie ->
        MovieDetailsBody(movie, pressOnBack)
    }
}

@Composable
private fun MovieDetailsBody(
    movie: Movie,
    pressOnBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
            .background(color = colorResource(R.color.bluegray_800))
    ) {

        ConstraintLayout {
            val (titleBar, image, rateRow, title, content) = createRefs()

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Config.base_url + movie.poster_path)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .aspectRatio(0.85f),
            )

            Text(
                text = movie.title,
                style = MaterialTheme.typography.h5.copy(color = Color.White),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(image.bottom)
                    }
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp)
            )

            Row(
                modifier = Modifier
                    .constrainAs(rateRow) {
                        top.linkTo(title.bottom)
                    }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    tint = Color.Red,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )

                Text(
                    text = movie.popularity.toString(),
                    style = MaterialTheme.typography.body2.copy(color = Color.White),
                    modifier = Modifier.padding(end = 16.dp, top = 4.dp)
                )

                Icon(
                    imageVector = Icons.Filled.StarRate,
                    tint = Color.Yellow,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )

                Text(
                    text = movie.vote_average.toString(),
                    style = MaterialTheme.typography.body2.copy(color = Color.White),
                    modifier = Modifier.padding(end = 32.dp, top = 4.dp)
                )

                Text(
                    text = "(${movie.release_date})",
                    style = MaterialTheme.typography.body2.copy(color = Color.White),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Text(
                text = movie.overview,
                style = MaterialTheme.typography.body2.copy(color = Color.White),
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(rateRow.bottom)
                    }
                    .padding(16.dp)
            )

            Surface(
                modifier = Modifier
                    .constrainAs(titleBar) {
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth(),
                color = colorResource(R.color.bluegray_800).copy(alpha = 0.7F),
            ) {
                ConstraintLayout {
                    val (arrow, title) = createRefs()

                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier
                            .constrainAs(arrow) {
                                start.linkTo(parent.start)
                            }
                            .padding(12.dp)
                            .clickable(onClick = { pressOnBack() })
                    )

                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.body2.copy(color = Color.White),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier
                            .constrainAs(title) {
                                centerHorizontallyTo(parent)
                                centerVerticallyTo(parent)
                            }
                            .padding(24.dp, 0.dp, 24.dp, 0.dp)
                    )
                }
            }
        }
    }
}