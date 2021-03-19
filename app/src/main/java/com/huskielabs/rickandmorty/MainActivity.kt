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
import androidx.navigation.compose.*
import com.huskielabs.rickandmorty.ui.screens.character.CharacterScreen
import com.huskielabs.rickandmorty.ui.theme.Gray1
import com.huskielabs.rickandmorty.ui.theme.Indigo
import com.huskielabs.rickandmorty.ui.theme.RickAndMortyTheme

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
                CharacterScreen()
            }
            composable(Screen.Location.route) {
//                Profile(navController)
            }
            composable(Screen.Episode.route) {
//                Profile(navController)
            }
        }
    }
}