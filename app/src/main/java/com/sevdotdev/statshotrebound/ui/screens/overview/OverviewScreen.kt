package com.sevdotdev.statshotrebound.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.sevdotdev.statshotrebound.domain.model.UserInfo
import com.sevdotdev.statshotrebound.ui.screens.overview.MatchViewItem
import com.sevdotdev.statshotrebound.ui.screens.overview.MatchViewState
import com.sevdotdev.statshotrebound.ui.screens.overview.OverViewViewModel

@Composable
fun OverViewScreenBuilder(
    viewModel: OverViewViewModel
) {
    val userInfo = viewModel.userStateInfo.collectAsState()
    val userStats = viewModel.recentMatchViewState.collectAsState()

    when (val info = userInfo.value) {
        UserInfo.NO_USER_IDENTIFIED_YET -> {
            RegisterPlayerInfoDialog(onInfoAdded = {
                viewModel.updatePlayerInfo(it.text)
            })
        }
        else -> {
            RecentMatchList(state = userStats.value)
        }
    }

}

@Composable
fun RecentMatchList(
    state: MatchViewState,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    LazyColumn{
        state.payload?.let { list ->
            items(list){
                MatchViewItem(matchState = it)
            }
        }
    }
}

@Composable
fun RegisterPlayerInfoDialog(
    onInfoAdded: (TextFieldValue) -> Boolean
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(text = "Add your Slap Id")
        },
        text = {
            TextField(value = textState.value, onValueChange = {
                textState.value = it
            })
        },
        confirmButton = {
            Button(onClick = { onInfoAdded(textState.value) }) {

            }
        }

    )
}

@Preview
@Composable
fun PreviewDialog() {
    Surface {

        RegisterPlayerInfoDialog(onInfoAdded = { true })
    }

}