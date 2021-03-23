package com.huskielabs.rickandmorty.ui.screens.episode

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.huskielabs.rickandmorty.R
import com.huskielabs.rickandmorty.shared.ui.FilterTopBar
import com.huskielabs.rickandmorty.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun EpisodeScreen(viewModel: EpisodeViewModelContract) {
    val episodes by viewModel.episodeList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            FilterTopBar(
                title = stringResource(id = R.string.episode),
                onFilterClicked = {}
            )
        }
    ) {
        EpisodeScreenContent(
            episodes = episodes,
            isLoading = isLoading,
            onEpisodeCLicked = { id ->

            }
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun EpisodeScreenContent(
    episodes: Map<String, List<EpisodeViewData>>,
    isLoading: Boolean,
    onEpisodeCLicked: (id: Int) -> Unit
) {
    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = Indigo,
                modifier = Modifier.size(48.dp)
            )
        }
    } else {
        val scrollState = rememberLazyListState()
        LazyColumn(state = scrollState) {
            episodes.keys.forEach { key ->
                stickyHeader { ListHeader(title = key) }
                val episodesList = episodes[key]!!
                items(episodesList.size) { index ->
                    ListItem(
                        episode = episodesList[index],
                        onEpisodeClicked = onEpisodeCLicked,
                    )
                    Divider(
                        color = DividerBackground,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ListHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        color = Gray1,
        modifier = Modifier
            .background(White)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp)
    )
}

@Composable
private fun ListItem(
    episode: EpisodeViewData,
    modifier: Modifier = Modifier,
    onEpisodeClicked: (id: Int) -> Unit
) {
    Row(modifier = modifier
        .clickable { onEpisodeClicked(episode.id) }
        .padding(start = 16.dp, top = 16.dp, bottom = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(text = episode.name, style = EpisodeTitleStyle, color = Black)
            Text(
                text = episode.episode,
                style = MaterialTheme.typography.caption,
                color = AdditionalText
            )
            Text(
                text = episode.airDate.toUpperCase(),
                style = MaterialTheme.typography.overline.copy(fontWeight = FontWeight.Medium),
                color = Gray1
            )
        }
    }
}

@Preview
@Composable
fun ListHeaderPreview() {
    ListHeader(title = "Season 1")
}

@Preview
@Composable
fun ListItemPreview() {
    val episode = EpisodeViewData(
        id = 1,
        name = "Pilot",
        episode = "S01E01",
        airDate = "DECEMBER 2, 2013"
    )
    ListItem(episode = episode) {

    }
}