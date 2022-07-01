package com.example.music.ui.screen.add_artist

import android.text.Layout
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isFinite
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import com.example.music.R
import com.example.music.data.model.artist.Artist
import com.example.music.ui.theme.MusicTheme
import com.example.music.ui.theme.roundedIcon
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch


private val GRID_SPACING = 8.dp

@Composable
fun AddArtist(viewModel: AddArtistVM) {

    AddArtistContent(
        viewModel = viewModel
    )
}

@Composable
private fun AddArtistContent(
    viewModel: AddArtistVM,
) {
    val state = viewModel.state.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }

    /*state.snackBarText?.let { message ->
        LaunchedEffect(message) {
            Log.d("zxc", "snackBarshown")
            snackbarHostState.showSnackbar(message)
        }

        viewModel.snackBarShown()
    }*/

    Column {
        Text(
            text = stringResource(id = R.string.add_artist_text),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 40.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        SearchBar(
            modifier = Modifier
                .heightIn(40.dp),
            onValueChange = { viewModel.search(it) }
        )

        Spacer(modifier = Modifier.height(25.dp))

        AddArtistArtistList(
            viewModel = viewModel
        )
    }

}

/*@Composable
private fun AddArtistContent(
    artists: List<Artist>,
    isRefreshing: Boolean
) {
    ConstraintLayout {
        val (text, search, list_of_artists) = createRefs()

        val contentStart = createGuidelineFromStart(10.dp)
        val contentEnd = createGuidelineFromEnd(10.dp)

        Text(
            text = stringResource(id = R.string.add_artist_text),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 40.sp,
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(contentStart)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }
                .fillMaxHeight()
        )

        SearchBar(modifier = Modifier
            .heightIn(40.dp)
            .constrainAs(search) {
                start.linkTo(contentStart)
                top.linkTo(text.bottom, 20.dp)
                end.linkTo(contentEnd)
                width = Dimension.fillToConstraints
            })

        AddArtistArtistList(
            artists = artists,
            modifier = Modifier.constrainAs(list_of_artists) {
                top.linkTo(search.bottom, 25.dp)
                bottom.linkTo(parent.bottom)
                end.linkTo(contentEnd)
                start.linkTo(contentStart)
                height = Dimension.fillToConstraints
            },
            isRefreshing = isRefreshing
        )
    }
}*/

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
    //text: String,
    //onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it.text)
        },
        modifier = modifier,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,

                )
        },
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black,
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true
    )
}

@Composable
private fun AddArtistArtistList(
    viewModel: AddArtistVM,
    modifier: Modifier = Modifier,
) {
    val viewState = viewModel.state.collectAsState().value

    SwipeRefresh(
        modifier = modifier,
        state = rememberSwipeRefreshState(isRefreshing = viewState.isRefreshing),
        onRefresh = { /*TODO*/ },
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                // Pass the SwipeRefreshState + trigger through
                state = state,
                refreshTriggerDistance = trigger,
                // Enable the scale animation
                scale = true,
                // Change the color and shape
                shape = MaterialTheme.shapes.small,
            )
        }
    ) {
        LazyVerticalGrid(
            modifier = modifier.fillMaxWidth(),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(
                GRID_SPACING,
                Alignment.CenterHorizontally
            ),
            contentPadding = PaddingValues(
                start = GRID_SPACING,
                end = GRID_SPACING,
                bottom = WindowInsets.navigationBars.getBottom(LocalDensity.current).dp.plus(
                    GRID_SPACING
                )
            ),
        ) {
            items(viewState.artists /*key = { artists -> artists.id }*/) { artist ->
                Artist(
                    name = artist.name,
                    picture = artist.images[0].url,
                    selected = {
                        //viewModel.artistsSelected(artist)
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddArtistArtist(
    name: String,
    picture: String
) {
    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.roundedIcon.medium),
                    shape = CircleShape
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = picture),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Text(
                name,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentWidth(Alignment.End)
            )
        }
    }
}

@Composable
fun Artist(
    name: String,
    picture: String,
    selected: (String) -> Unit
) {
    var isSelected by remember {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                isSelected = !isSelected
                selected(name)
            },
    ) {
        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = picture),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .height(110.dp),
                contentScale = ContentScale.Crop,
            )
            if (isSelected) {
                Image(
                    painter = painterResource(id = R.drawable.ic_round_check_circle_24),
                    contentDescription = "",
                )
            }
        }
        Text(text = name, modifier = Modifier.wrapContentWidth(Alignment.End))
    }
}

@Preview(showSystemUi = false)
@Composable
fun DefaultPreview() {
    MusicTheme {
        //Check(true)
    }
}