package com.huskielabs.rickandmorty.shared.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.huskielabs.rickandmorty.R
import com.huskielabs.rickandmorty.ui.theme.Black
import com.huskielabs.rickandmorty.ui.theme.Indigo

@Composable
fun FilterTopBar(title: String, onFilterClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                color = Black,
            )
        },
        actions = {
            IconButton(onClick = onFilterClicked) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.ic_baseline_filter_list_24
                    ),
                    contentDescription = null,
                    tint = Indigo
                )
            }
        }
    )
}