package com.huskielabs.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.*
import com.huskielabs.rickandmorty.shared.Navigator
import com.huskielabs.rickandmorty.ui.screens.character.CharacterScreen
import com.huskielabs.rickandmorty.ui.screens.character.CharacterViewModel
import com.huskielabs.rickandmorty.ui.screens.character.CharacterViewModelContract
import com.huskielabs.rickandmorty.ui.screens.character.filter.*
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
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @ExperimentalMaterialApi
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

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @Composable
    private fun Navigation() {
        val navController = rememberNavController()
        navigator.bind(navController = navController, lifecycleScope = lifecycleScope)

        val scaffoldState = rememberScaffoldState()
        var isBottomNavigationVisible by remember { mutableStateOf(true) }

        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                if (isBottomNavigationVisible) {
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
            }
        ) {
            NavHost(navController, startDestination = BottomNavScreen.Character.route) {
                composable(BottomNavScreen.Character.route) { backStackEntry ->
                    isBottomNavigationVisible = true

                    val filter = backStackEntry.savedStateHandle
                        .get<CharacterFilterViewData>(ARG_CHARACTER_FILTER)
                    Log.d(TAG, "Navigation: gender = ${filter?.gender}")
                    Log.d(TAG, "Navigation: status = ${filter?.status}")

                    val viewModel: CharacterViewModelContract =
                        hiltNavGraphViewModel<CharacterViewModel>(backStackEntry)

                    if (filter != null) {
                        viewModel.setFilter(filter)
                    }

                    backStackEntry.savedStateHandle.set(ARG_CHARACTER_FILTER, null)

                    CharacterScreen(viewModel)
                }
                composable(BottomNavScreen.Location.route) {
                    isBottomNavigationVisible = true
                    val viewModel: LocationViewModelContract =
                        hiltNavGraphViewModel<LocationViewModel>(it)
                    LocationScreen(viewModel)
                }
                composable(BottomNavScreen.Episode.route) {
                    isBottomNavigationVisible = true
                    val viewModel: EpisodeViewModelContract =
                        hiltNavGraphViewModel<EpisodeViewModel>(it)
                    EpisodeScreen(viewModel)
                }
                composable(Screen.CharacterFilter.route) {
                    isBottomNavigationVisible = false

                    val viewModel: CharacterFilterViewModelContract =
                        hiltNavGraphViewModel<CharacterFilterViewModel>(it)
                    CharacterFilterScreen(viewModel)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.unbind()
    }
}

private const val TAG = "MainActivity"

private val items = listOf(
    BottomNavScreen.Character,
    BottomNavScreen.Location,
    BottomNavScreen.Episode,
)