package com.huskielabs.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.*
import com.huskielabs.rickandmorty.ui.screens.character.CharacterScreen
import com.huskielabs.rickandmorty.ui.screens.character.CharacterViewModel
import com.huskielabs.rickandmorty.ui.screens.character.CharacterViewModelContract
import com.huskielabs.rickandmorty.ui.screens.episode.EpisodeScreen
import com.huskielabs.rickandmorty.ui.screens.episode.EpisodeViewModel
import com.huskielabs.rickandmorty.ui.screens.episode.EpisodeViewModelContract
import com.huskielabs.rickandmorty.ui.screens.location.LocationScreen
import com.huskielabs.rickandmorty.ui.screens.location.LocationViewModel
import com.huskielabs.rickandmorty.ui.screens.location.LocationViewModelContract
import com.huskielabs.rickandmorty.ui.theme.Gray1
import com.huskielabs.rickandmorty.ui.theme.Indigo
import com.huskielabs.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation()
                }
            }
        }
    }
}


private val items = listOf(
    Screen.Character,
    Screen.Location,
    Screen.Episode,
)

@ExperimentalFoundationApi
@Composable
private fun Navigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                items.forEach { screen ->
                    val isSelected = currentRoute == screen.route
                    val icon = if (isSelected) screen.iconSelected else screen.icon
                    val color = if (isSelected) Indigo else Gray1
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = icon),
                                contentDescription = stringResource(screen.resourceId),
                                tint = color
                            )
                        },
                        label = {
                            Text(
                                stringResource(screen.resourceId),
                                color = color,
                                style = MaterialTheme.typography.caption
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo = navController.graph.startDestination
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(navController, startDestination = Screen.Character.route) {
            composable(Screen.Character.route) {
                val viewModel: CharacterViewModelContract =
                    hiltNavGraphViewModel<CharacterViewModel>(it)
                CharacterScreen(viewModel)
            }
            composable(Screen.Location.route) {
                val viewModel: LocationViewModelContract =
                    hiltNavGraphViewModel<LocationViewModel>(it)
                LocationScreen(viewModel)
            }
            composable(Screen.Episode.route) {
                val viewModel: EpisodeViewModelContract =
                    hiltNavGraphViewModel<EpisodeViewModel>(it)
                EpisodeScreen(viewModel)
            }
        }
    }
}