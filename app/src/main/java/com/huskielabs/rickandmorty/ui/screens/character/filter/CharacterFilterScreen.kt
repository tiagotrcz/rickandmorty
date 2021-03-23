package com.huskielabs.rickandmorty.ui.screens.character.filter

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.huskielabs.rickandmorty.R
import com.huskielabs.rickandmorty.shared.ui.TopBarTitleText
import com.huskielabs.rickandmorty.ui.theme.DividerBackground
import com.huskielabs.rickandmorty.ui.theme.Gray1
import com.huskielabs.rickandmorty.ui.theme.Indigo
import com.huskielabs.rickandmorty.ui.theme.White
import java.util.*

@Composable
fun CharacterFilterScreen(viewModel: CharacterFilterViewModelContract) {
    Scaffold(
        topBar = {
            TopAppBar(title = { TopBarTitleText(title = stringResource(id = R.string.filter)) })
        }
    ) {
        FilterScreenContent(onApply = viewModel::applyFilter)
    }
}

@Composable
private fun FilterScreenContent(onApply: (CharacterFilterViewData) -> Unit) {
    val (selectedStatus, onStatusSelected) = remember { mutableStateOf("") }
    val (selectedGender, onGenderSelected) = remember { mutableStateOf("") }

    val applyFilter = {
        onApply(CharacterFilterViewData(selectedStatus, selectedGender))
    }

    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            RadioList(
                title = stringResource(id = R.string.status),
                list = listOf("Alive", "Dead", "Unknown"),
                selected = selectedStatus,
                onSelected = onStatusSelected
            )
            RadioList(
                title = stringResource(id = R.string.gender),
                list = listOf("Female", "Male", "Genderless"),
                selected = selectedGender,
                onSelected = onGenderSelected
            )
        }
        Button(
            onClick = applyFilter,
            colors = ButtonDefaults.buttonColors(backgroundColor = Indigo),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.apply).toUpperCase(Locale.ROOT),
                style = MaterialTheme.typography.button,
                color = White,
            )
        }
    }
}

@Composable
private fun RadioList(
    title: String,
    list: List<String>,
    selected: String,
    onSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 20.dp, top = 20.dp, bottom = 6.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body2,
            color = Gray1
        )
        Divider(color = Color.Transparent, modifier = Modifier.height(16.dp))
        list.forEach { status ->
            Row(modifier = Modifier.padding(top = 14.dp, bottom = 14.dp, end = 20.dp)) {
                RadioButton(
                    selected = selected == status,
                    onClick = { onSelected(status) },
                    colors = RadioButtonDefaults.colors(selectedColor = Indigo)
                )
                Text(
                    text = status,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 18.dp)
                )
            }
            Divider(
                color = DividerBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
            )
        }
    }
}

@Preview
@Composable
private fun FilterScreenContentPreview() {
    FilterScreenContent {}
}
