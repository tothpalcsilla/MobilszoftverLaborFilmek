package com.example.mobilszoftverlabormovies.ui.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.ui.list.ListViewModel
import com.example.mobilszoftverlabormovies.R

@Composable
fun Movies(
    viewModel: ListViewModel,
    selectMovie: (Long) -> Unit,
    selectMenu: (Int) -> Unit,
) {
    val movies: List<Movie> by viewModel.movieList.collectAsState(initial = listOf())
    val isLoading: Boolean by viewModel.isLoading
    val selectedMenu: Int by viewModel.selectedMenu

    ConstraintLayout {
        val (body, progress) = createRefs()
        Scaffold(
            backgroundColor = colorResource(R.color.white),
            topBar = {
                MovieAppBar(selectedMenu, fun(i: Int) { viewModel.selectMenu(i) }, selectMenu)
            },
            modifier = Modifier.constrainAs(body) {
                top.linkTo(parent.top)
            },
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            HomeMovies(modifier, movies, selectMovie)
        }
        if (isLoading) CircularProgressIndicator(
            modifier = Modifier
                .constrainAs(progress) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            color = colorResource(R.color.bluegray_900)
        )
    }
}

@Composable
private fun MovieAppBar(
    selectedMenuIndex: Int,
    selectMenuItem: (Int) -> Unit,
    selectMenu: (Int) -> Unit
) {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = colorResource(R.color.bluegray_900),
        modifier = Modifier.height(58.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            text = stringResource(R.string.app_name),
            color = colorResource(R.color.white),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        MyDropDown(selectedMenuIndex, selectMenuItem, selectMenu)
    }
}

@Composable
private fun MyDropDown(
    selectedMenuIndex: Int,
    selectMenuItem: (Int) -> Unit,
    selectMenu: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val items: List<String> = listOf(
        stringResource(R.string.menu_home),
        stringResource(R.string.menu_top_rated),
        stringResource(R.string.menu_popular),
        stringResource(R.string.menu_now_playing)
    )
    var selectedIndex by remember { mutableStateOf(selectedMenuIndex) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.CenterEnd)
    ) {
        Icon(
            imageVector = Icons.Filled.Menu,
            tint = colorResource(R.color.white),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
                .clickable(onClick = { expanded = true })
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(
                    colorResource(R.color.bluegray_900)
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    Config.menuIndex = selectedIndex
                    expanded = false
                    selectMenuItem(selectedIndex)
                    selectMenu(selectedIndex)
                }) {
                    val fontWeight = if (selectedIndex == index) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    }
                    Text(
                        text = s,
                        style = MaterialTheme.typography.body2.copy(
                            color = colorResource(R.color.white),
                            fontWeight = fontWeight
                        )
                    )
                }
            }
        }
    }
}