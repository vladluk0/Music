package com.example.music.ui.screen.library

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.music.R
import com.example.music.Screen
import com.example.music.ui.screen.add_artist.AddArtist
import com.example.music.ui.theme.MusicTheme
import com.example.music.ui.theme.roundedIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Library(
    addNewArtist: () -> Unit,
    viewModel: LibraryVM
) {
    Scaffold(
        topBar = {
            LibraryAppBar()
        }
    ) { values ->
        Column(modifier = Modifier.padding(values)) {
            LibraryContent(
                viewModel,
                addNewArtist = addNewArtist
            )
        }
    }
}

@Composable
fun LibraryContent(
    viewModel: LibraryVM,
    addNewArtist: () -> Unit
) {
    val listState = rememberLazyListState()

    var offset by remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                offset += available.y
                viewModel.scroll(offset + available.y)
                return Offset.Zero
            }
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .padding(top = 5.dp)
            .nestedScroll(nestedScrollConnection)
    ) {
        items(20) {
            AddNewArtist(addNewArtist = addNewArtist)
        }
    }
}

@Composable
fun LibraryAppBar() {
    Column {
        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Моя бібліотека",
                fontSize = 20.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = "",
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = ""
                )
            }
        }
        Row {
            Category(text = "Плейлісти")
            Category(text = "Виконавці")
        }

        Spacer(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(Color.Black)
        )
    }
}

@Composable
fun Category(
    text: String
) {
    Surface(
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 1.dp)
        )
    }

}

@Composable
fun AddNewArtist(
    addNewArtist: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                Log.d("zxc", "clickable")
                addNewArtist.invoke()
            }
            .fillMaxWidth()
    ) {

        ArtistIcon()

        Spacer(modifier = Modifier.width(8.dp))

        BasicText(
            text = "Додати виконавців",
            modifier = Modifier.height(30.dp),
        )
    }
}

@Composable
fun ArtistIcon() {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(MaterialTheme.roundedIcon.small)
            .background(Color.Gray),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_add_24),
            contentDescription = "",
            modifier = Modifier.size(30.dp)
        )
    }
}

@Preview(showSystemUi = false)
@Composable
fun DefaultPreview() {
    MusicTheme {
        //AddNewArtist(addNewArtist = {})
        LibraryAppBar()
        //Category("qwerty")
    }
}