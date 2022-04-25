package com.sevdotdev.statshotrebound

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sevdotdev.statshotrebound.data.network.MatchService
import com.sevdotdev.statshotrebound.ui.theme.StatShotReboundTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var matchService: MatchService

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StatShotReboundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    color = MaterialTheme.colors.background
                ) {
                    val names = listOf(
                        "Kendall", "Roman", "Shiv", "Marcia", "Connor"
                    )

                    Greeting(
                        "Android", names = names
                    )

                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun Greeting(name: String, names: List<String>) {
    Text(text = "Hello $name!")
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            Box(
                modifier = Modifier.padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "The Roys are insane", style = MaterialTheme.typography.body1)
            }
        }
        items(names) { name ->
            val context = LocalContext.current
            Card(
                onClick = {
                    Toast.makeText(context, "$name was clicked", Toast.LENGTH_LONG).show()
                },
                border = BorderStroke(width = 2.dp, color = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "you know $name",
                    modifier = Modifier.padding(start = 10.dp)
                )

            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StatShotReboundTheme {
        Greeting("Android", listOf())
    }
}