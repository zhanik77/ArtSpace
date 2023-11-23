package kz.just_code.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kz.just_code.artspace.ui.theme.ArtSpaceTheme




import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
private fun ArtSpace() {

    var artworkIndex by remember { mutableStateOf(0) }
    if(artworkIndex == 4) artworkIndex = 0
    if(artworkIndex == -1) artworkIndex = 3

    val artwork: Int
    val artworkDescription: Int
    val artworkName: Int
    val artworkReleaseYear: Int

    when (artworkIndex) {
        0 -> {
            artwork = R.drawable.android__berlin_wall
            artworkDescription = R.string.android_berlin_wall_description
            artworkName = R.string.berliner_android_title
            artworkReleaseYear = R.string.twentytwentyone
        }
        1 -> {
            artwork = R.drawable.android__happy_party_animal_
            artworkDescription = R.string.android_party_animal_description
            artworkName = R.string.party_animal_android_title
            artworkReleaseYear = R.string.twentytwentyone
        }
        2 -> {
            artwork = R.drawable.android__humble_
            artworkDescription = R.string.android_humble_description
            artworkName = R.string.humble_android_title
            artworkReleaseYear = R.string.twentytwentytwo
        }
        else -> {
            artwork = R.drawable.android__snowflake_
            artworkDescription = R.string.android_snowflake_description
            artworkName = R.string.snowflake_android_title
            artworkReleaseYear = R.string.twentytwentythree
        }
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.card_white)),
            shape = RectangleShape,
        ) {
            Image(
                painter = painterResource(id = artwork),
                contentDescription = stringResource(id = artworkDescription),
                modifier = Modifier.padding(30.dp),
            )
        }

        BoxWithConstraints {
            if(maxWidth < 413.dp) {
                Spacer(Modifier.height(76.dp))
            } else if (maxWidth < 1281.dp) {
                Spacer(Modifier.height(20.dp))
            }
        }

        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(colorResource(id = R.color.artwork_title_black), fontWeight = FontWeight.Light, fontSize = 24.sp)
                ) {
                    append(stringResource(id = artworkName))
                }

                withStyle(
                    style = SpanStyle(colorResource(id = R.color.artwork_author_black), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                ) {
                    append(stringResource(id = R.string.author_dalle))
                }

                withStyle(
                    style = SpanStyle(colorResource(id = R.color.artwork_release_year_black),  fontWeight = FontWeight.Light, fontSize = 16.sp)
                ) {
                    append(stringResource(id = artworkReleaseYear))
                }
            },

            modifier = Modifier
                .background(colorResource(id = R.color.artwork_info_background))
                .padding(16.dp),
        )

        Spacer(Modifier.height(28.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            EditButtons(
                onClick = { artworkIndex-- },
                buttonColor = R.color.button_blue,
                buttonText = R.string.previous_button,
            )

            EditButtons(
                onClick = { artworkIndex++ },
                buttonColor = R.color.button_blue,
                buttonText = R.string.next_button,
            )
        }
    }
}

@Composable
fun EditButtons(onClick: () -> Unit, @StringRes buttonText: Int, @ColorRes buttonColor: Int, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(colorResource(id = buttonColor)),
        modifier = modifier.width(140.dp),
    ) {
        Text(text = stringResource(id = buttonText))
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}