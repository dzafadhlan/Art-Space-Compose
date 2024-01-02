package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import kotlin.jvm.Throws

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtImage()
                }
            }
        }
    }
}

@Composable
fun ArtImage(modifier: Modifier = Modifier) {
    val listImage = listOf(
        R.drawable.image_1,
        R.drawable.image_2,
        R.drawable.image_3
    )
    var currentIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        ImagePreview(
            Image = if (listImage.isNotEmpty() && currentIndex in 0 until listImage.size) {
                painterResource(id = listImage[currentIndex])
            } else {
                painterResource(id = R.drawable.image_1)
            }
        )
        TextTitle(title = "Hello", year = "1998")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            BtnImage(
                textBtn = "Previous",
                onClick = { if (currentIndex > 0) currentIndex-- },
            )
            BtnImage(textBtn = "Next",
                onClick = { if (currentIndex < listImage.size - 1) currentIndex++ }
            )
        }

    }

}

@Composable
fun ImagePreview(
    modifier: Modifier = Modifier,
    Image: Painter
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Card(
            modifier = modifier
                .align(alignment = Alignment.Center)
                .padding(top = 20.dp)
        ) {
            Image(
                painter = Image, contentDescription = null,
                modifier = modifier
                    .padding(20.dp)
            )
        }
    }

}

@Composable
fun TextTitle(
    title: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(40.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(color = Color.LightGray, shape = MaterialTheme.shapes.extraSmall)
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.40f)
        ) {
            Text(text = title, modifier.align(alignment = Alignment.CenterHorizontally))
            Text(
                text = year,
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp
                ),

                )
        }
    }

}

@Composable
fun BtnImage(
    modifier: Modifier = Modifier,
    textBtn: String,
    onClick: () -> Unit
) {

    Button(onClick = onClick) {
        Text(text = textBtn, modifier = modifier)
    }


}


@Preview
@Composable
fun Preview() {
    ArtImage(modifier = Modifier.fillMaxSize())

}