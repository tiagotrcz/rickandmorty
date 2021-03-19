package com.huskielabs.rickandmorty.ui.screens.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage
import com.huskielabs.rickandmorty.ui.theme.AdditionalText
import com.huskielabs.rickandmorty.ui.theme.Black
import com.huskielabs.rickandmorty.ui.theme.Gray6
import com.huskielabs.rickandmorty.ui.theme.White

val list = listOf(
    CharacterViewData(
        id = 0,
        image = "https://rickandmortyapi.com/api/character/avatar/11.jpeg",
        name = "Albert Einstein",
        status = "Alive"
    ),
    CharacterViewData(
        id = 0,
        image = "https://rickandmortyapi.com/api/character/avatar/11.jpeg",
        name = "Albert Einstein",
        status = "Alive"
    ),
    CharacterViewData(
        id = 0,
        image = "https://rickandmortyapi.com/api/character/avatar/11.jpeg",
        name = "Albert Einstein",
        status = "Alive"
    )
)

@ExperimentalFoundationApi
@Composable
fun CharacterScreen() {
    Scaffold {
        CharacterScreenContent(list)
    }
}

@ExperimentalFoundationApi
@Composable
private fun CharacterScreenContent(list: List<CharacterViewData>) {
    val scrollState = rememberLazyListState()
    LazyVerticalGrid(
        state = scrollState,
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 20.dp),
    ) {
        items(list) { character ->
            CharacterItem(character = character)
        }
    }
}

@Composable
private fun CharacterItem(character: CharacterViewData) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 10.dp)
            .clickable { }
            .width(150.dp)
            .height(222.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = Gray6, shape = RoundedCornerShape(8.dp))
            .background(White)
    ) {
        CoilImage(
            data = character.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = character.status,
                color = AdditionalText,
                style = MaterialTheme.typography.caption
            )
            Text(
                text = character.name,
                color = Black,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Preview
@Composable
private fun CharacterItemPreview() {
    val character = CharacterViewData(
        id = 0,
        image = "https://rickandmortyapi.com/api/character/avatar/11.jpeg",
        name = "Albert Einstein",
        status = "Alive"
    )
    CharacterItem(character)
}