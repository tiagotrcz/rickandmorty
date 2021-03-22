package com.huskielabs.rickandmorty

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class BottomNavScreen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val iconSelected: Int,
) {
    object Character : BottomNavScreen(
        route = "character",
        resourceId = R.string.character,
        icon = R.drawable.ic_ghost,
        iconSelected = R.drawable.ic_ghost_filled
    )

    object Location : BottomNavScreen(
        route = "location",
        resourceId = R.string.location,
        icon = R.drawable.ic_planet,
        iconSelected = R.drawable.ic_planet_filled
    )

    object Episode : BottomNavScreen(
        route = "episode",
        resourceId = R.string.episode,
        icon = R.drawable.ic_tv,
        iconSelected = R.drawable.ic_tv_filled
    )
}