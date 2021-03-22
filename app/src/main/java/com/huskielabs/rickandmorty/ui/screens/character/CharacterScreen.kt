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
import com.huskielabs.rickandmorty.shared.lastIndexForPagination
import com.huskielabs.rickandmorty.shared.ui.FilterTopBar
import com.huskielabs.rickandmorty.shared.ui.skeletonAnim
import com.huskielabs.rickandmorty.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun CharacterScreen(viewModel: CharacterViewModelContract) {
    val characters by viewModel.characterList.collectAsState(initial = emptyList())
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
            characters = characters,
            isLoading = isLoading,
            getMoreItems = viewModel::getCharacters
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun CharacterScreenContent(
    characters: List<CharacterViewData>,
    isLoading: Boolean,
    getMoreItems: () -> Unit
) {
    val scrollState = rememberLazyListState()
    val lastIndex = characters.lastIndexForPagination

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
        itemsIndexed(characters) { index, character ->
            CharacterItem(character)

            if (index == lastIndex) {
                getMoreItems()
            }
        }


        if (isLoading) {
            items(3) {
                LoadingCharacterItem()
            }
        }
    }
}

@Composable
private fun CharacterItem(character: CharacterViewData) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 10.dp)
            .clickable { }
            .size(width = 150.dp, height = 222.dp)
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

@Composable
private fun LoadingCharacterItem() {
    val alpha = skeletonAnim()
    val borderColor = Gray6.copy(alpha = alpha)
    val boxColor = Gray4.copy(alpha = alpha)
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 10.dp)
            .size(width = 150.dp, height = 222.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(8.dp))
            .background(White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(boxColor)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(width = 80.dp, height = 12.dp)
                    .background(boxColor)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .size(width = 180.dp, height = 16.dp)
                    .background(boxColor)
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

@Preview
@Composable
private fun LoadingCharacterItemPreview() {
    LoadingCharacterItem()
}