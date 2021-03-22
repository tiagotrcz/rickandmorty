package com.huskielabs.rickandmorty.ui.screens.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage
import com.huskielabs.rickandmorty.R
import com.huskielabs.rickandmorty.shared.ui.FilterTopBar
import com.huskielabs.rickandmorty.ui.theme.AdditionalText
import com.huskielabs.rickandmorty.ui.theme.Black
import com.huskielabs.rickandmorty.ui.theme.Gray6
import com.huskielabs.rickandmorty.ui.theme.White

@ExperimentalFoundationApi
@Composable
fun CharacterScreen(viewModel: CharacterViewModelContract) {
    val list by viewModel.characterList.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            FilterTopBar(
                title = stringResource(id = R.string.character),
                onFilterClicked = {}
            )
        }
    ) {
        CharacterScreenContent(
            list = list,
            isLoading = isLoading,
            getMoreItems = viewModel::getCharacters
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun CharacterScreenContent(
    list: List<CharacterViewData>,
    isLoading: Boolean,
    getMoreItems: () -> Unit
) {
    Column {
        val scrollState = rememberLazyListState()
        val lastIndex = if (list.lastIndex <= 4) list.lastIndex else list.lastIndex - 4

        LazyVerticalGrid(
            state = scrollState,
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 14.dp,
                top = 20.dp,
                end = 14.dp,
                bottom = 50.dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(list) { index, character ->
                CharacterItem(character)

                if (index == lastIndex) {
                    getMoreItems()
                }
            }
        }

        //TODO find a way to add this to the bottom of the list
//        if (isLoading || list.isEmpty()) {
//            Box(
//                contentAlignment = Alignment.Center, modifier = Modifier
//                    .fillMaxWidth()
//                    .height(70.dp)
//            ) {
//                CircularProgressIndicator(
//                    color = Indigo,
//                    modifier = Modifier.size(48.dp)
//                )
//            }
//
//        }
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