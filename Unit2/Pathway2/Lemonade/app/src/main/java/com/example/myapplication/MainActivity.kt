package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    painterResource(R.drawable.lemon_tree),
                    stringResource(R.string.lemon_select),
                    stringResource(R.string.lemon_tree_content_description),
                    Modifier
                        .wrapContentSize()
                        .clickable { currentStep = 2 })

            }
            2 -> {
                var pressureStep by remember { mutableStateOf(0) }
                var pressureCount = (2..4).random()
                LemonTextAndImage(
                    painterResource(R.drawable.lemon_squeeze),
                    stringResource(R.string.lemon_squeeze),
                    stringResource(R.string.lemon_content_description),
                    Modifier
                        .wrapContentSize()
                        .clickable {
                            pressureStep++
                            if (pressureStep == pressureCount) {
                                currentStep = 3
                            }
                        })
            }
            3 -> {
                LemonTextAndImage(
                    painterResource(R.drawable.lemon_drink),
                    stringResource(R.string.lemon_drink),
                    stringResource(R.string.lemon_glass_description),
                    Modifier
                        .wrapContentSize()
                        .clickable { currentStep = 4 })

            }
            4 -> {
                LemonTextAndImage(
                    painterResource(R.drawable.lemon_restart),
                    stringResource(R.string.lemon_empty),
                    stringResource(R.string.lemon_empty_description),
                    Modifier
                        .wrapContentSize()
                        .clickable { currentStep = 1 })

            }
        }
    }
}

@Composable
fun LemonTextAndImage(image: Painter, text: String, description: String, modifier: Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = text, fontSize = (18.sp))
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = image,
            contentDescription = description,
            modifier = modifier
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}