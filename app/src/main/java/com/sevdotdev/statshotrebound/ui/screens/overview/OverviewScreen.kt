package com.sevdotdev.statshotrebound.ui.screens

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.sevdotdev.statshotrebound.domain.model.UserInfo
import com.sevdotdev.statshotrebound.ui.screens.overview.OverViewViewModel

@Composable
fun OverViewScreenBuilder(
    viewModel: OverViewViewModel
) {
    val userInfo = viewModel.userStateInfo.collectAsState()
    val userStats = viewModel.playerStatsOverviewState.collectAsState()

    when(val info = userInfo.value) {
        UserInfo.NO_USER_IDENTIFIED_YET -> {
            RegisterPlayerInfoDialog(onInfoAdded = {
                viewModel.updatePlayerInfo(it.text)
            })
        }
    }

}

@Composable
fun RegisterPlayerInfoDialog(
    onInfoAdded: (TextFieldValue) -> Boolean
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    AlertDialog(
        onDismissRequest = {  },
        title = {
                Text(text = "Add your Slap Id" )
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
    RegisterPlayerInfoDialog(onInfoAdded = {true})

}