package com.huskielabs.rickandmorty.ui.screens.location

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.huskielabs.rickandmorty.R
import com.huskielabs.rickandmorty.shared.lastIndexForPagination
import com.huskielabs.rickandmorty.shared.ui.FilterTopBar
import com.huskielabs.rickandmorty.shared.ui.skeletonAnim
import com.huskielabs.rickandmorty.ui.theme.AdditionalText
import com.huskielabs.rickandmorty.ui.theme.Black
import com.huskielabs.rickandmorty.ui.theme.Gray4
import com.huskielabs.rickandmorty.ui.theme.Gray6

@ExperimentalFoundationApi
@Composable
fun LocationScreen(viewModel: LocationViewModelContract) {
    val locations by viewModel.locationList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LocationScreenContent(
        locations = locations,
        isLoading = isLoading,
        onLocationClicked = { id -> },
        getMoreItems = viewModel::getLocations
    )
}

@ExperimentalFoundationApi
@Composable
private fun LocationScreenContent(
    locations: List<LocationViewData>,
    isLoading: Boolean,
    onLocationClicked: (id: Int) -> Unit,
    getMoreItems: () -> Unit,
) {
    Scaffold(
        topBar = {
            FilterTopBar(title = stringResource(id = R.string.location)) {}
        }
    ) {
        val scrollState = rememberLazyListState()
        val lastIndex = locations.lastIndexForPagination

        LazyVerticalGrid(
            state = scrollState,
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 10.dp,
                top = 20.dp,
                end = 10.dp,
                bottom = 70.dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(locations) { index, location ->
                LocationListItem(location = location, onLocationClicked = onLocationClicked)

                if (index == lastIndex) {
                    getMoreItems()
                }
            }

            if (isLoading) {
                items(3) {
                    LoadingLocationListItem()
                }
            }
        }
    }
}

@Composable
private fun LocationListItem(location: LocationViewData, onLocationClicked: (id: Int) -> Unit) {
    Column(modifier = Modifier
        .padding(10.dp)
        .clickable { onLocationClicked(location.id) }
        .size(width = 150.dp, height = 83.dp)
        .clip(RoundedCornerShape(8.dp))
        .border(width = 1.dp, color = Gray6, shape = RoundedCornerShape(8.dp))
        .padding(12.dp)
    ) {
        Text(
            text = location.type,
            style = MaterialTheme.typography.caption,
            color = AdditionalText
        )
        Text(
            text = location.name,
            style = MaterialTheme.typography.subtitle1,
            color = Black
        )
    }
}

@Composable
private fun LoadingLocationListItem() {
    val alpha = skeletonAnim()
    val borderColor = Gray6.copy(alpha = alpha)
    val boxColor = Gray4.copy(alpha = alpha)
    Column(
        modifier = Modifier
            .padding(10.dp)
            .size(width = 150.dp, height = 83.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 60.dp, height = 12.dp)
                .background(boxColor)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .size(width = 110.dp, height = 16.dp)
                .background(boxColor)
        )
    }
}

@Preview
@Composable
private fun LocationListItemPreview() {
    val location = LocationViewData(
        id = 1,
        type = "Planet",
        name = "Earth"
    )
    LocationListItem(location = location, onLocationClicked = { })
}